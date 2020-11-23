package com.lwxf.industry4.webapp.facade.wxapi.factory.supplier.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.supplier.MaterialService;
import com.lwxf.industry4.webapp.bizservice.supplier.SupplierProductService;
import com.lwxf.industry4.webapp.bizservice.supplier.SupplierService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.supplier.MaterialDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.industry4.webapp.domain.entity.supplier.SupplierProduct;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.supplier.SupplierFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

@Component("wxsupplierFacade")
public class SupplierFacadeImpl extends BaseFacadeImpl implements SupplierFacade {

    @Resource(name = "supplierService")
    private SupplierService supplierService;
    @Resource(name = "supplierProductService")
    private SupplierProductService supplierProductService;
    @Resource(name = "uploadFilesService")
    private UploadFilesService uploadFilesService;
    @Resource(name = "materialService")
    private MaterialService materialService;

    @Override
    public RequestResult findAllSupplierList(Integer pageNum, Integer pageSize, MapContext mapContext) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        paginatedFilter.setFilters(mapContext);
        Map<String,String> created = new HashMap<String, String>();
        created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
        List sort = new ArrayList();
        sort.add(created);
        paginatedFilter.setSorts(sort);
        PaginatedList<SupplierDtoFowWx> list = this.supplierService.selectDtoByFilter(paginatedFilter);
        MapContext mapparam = MapContext.newOne();
        for(SupplierDtoFowWx obj : list.getRows()){
            //给供应商绑定附件
            List<UploadFiles> supplierFiles = uploadFilesService.findByResourceId(obj.getId());
            obj.setFiles(supplierFiles);
            //给供应商绑定产品
            mapparam.put("supplierId",obj.getId());
            mapparam.put("branchId",mapContext.get("branchId"));
            List<SupplierProduct> products = supplierProductService.selectAllSupplierProduct(mapparam);
            //List<MaterialDto> materialDtos=this.materialService.findAllMaterials(mapparam);
            //给供应商产品绑定附件
            for(SupplierProduct ma : products){
                List<UploadFiles> prodFiles = uploadFilesService.findByResourceId(ma.getId());
                ma.setFiles(prodFiles);
            }
            obj.setListProduct(products);
        }
        return ResultFactory.generateRequestResult(this.supplierService.selectDtoByFilter(paginatedFilter));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addSupplier(Supplier supplier) {
        supplier.setCreated(new Date());
        this.supplierService.add(supplier);
        //更新图片信息为正常
        if(supplier.getFileIds()!=null && !supplier.getFileIds().equals("")){
            String[] ids = supplier.getFileIds().split(",");
            for(String file_id: ids){
                MapContext map = MapContext.newOne();
                map.put("id",file_id);
                map.put("status",1);
                this.uploadFilesService.updateByMapContext(map);
            }
        }
        return ResultFactory.generateRequestResult(supplier);
    }

    @Override
    public RequestResult viewSupplierInfo(String id) {
        SupplierDtoFowWx dto = supplierService.selectDtoById(id);
        MapContext map = MapContext.newOne();
        map.put("supplierId",id);
        dto.setFiles(uploadFilesService.findByResourceId(id));
       List<SupplierProduct> spList =supplierProductService.selectAllSupplierProduct(map);
        //List<MaterialDto> materialDtos=this.materialService.findAllMaterials(map);
        dto.setFiles(uploadFilesService.findByResourceId(id));
        if(spList!=null && spList.size()>0){
            for(SupplierProduct ma : spList){
                ma.setFiles(uploadFilesService.findByResourceId(id));
            }
        }
        dto.setListProduct(spList);
        return ResultFactory.generateRequestResult(dto);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult uploadImage(List<MultipartFile> multipartFileList) {
        //添加图片到本地
        List<MapContext> listUrls = new ArrayList<>();
        if(multipartFileList!=null && multipartFileList.size()>0){
            for(MultipartFile multipartFile:multipartFileList){
                UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.PAYMENT_SIMPLE, "cover");
                //添加图片到数据库
                UploadFiles uploadFiles = new UploadFiles();
                uploadFiles.setCreated(DateUtil.getSystemDate());
                uploadFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
                uploadFiles.setMime(uploadInfo.getFileMimeType().getRealType());
                uploadFiles.setName(uploadInfo.getFileName());
                uploadFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
                uploadFiles.setPath(uploadInfo.getRelativePath());
                uploadFiles.setStatus(0);
                uploadFiles.setCreator(WebUtils.getCurrUserId());
                uploadFiles.setResourceType(UploadResourceType.SUPPLIER_PRODUCT_FILE.getType());
                this.uploadFilesService.add(uploadFiles);
                MapContext map = MapContext.newOne();
                map.put("imgFullPath",uploadFiles.getFullPath());
                map.put("imgRelPath",uploadInfo.getRelativePath());
                map.put("fileId",uploadFiles.getId());
                listUrls.add(map);
            }
        }
        return ResultFactory.generateRequestResult(listUrls);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateSupplier(String supplierId, MapContext map) {
        map.put("id",supplierId);
        return ResultFactory.generateRequestResult(this.supplierService.updateByMapContext(map));
    }
}
