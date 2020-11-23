package com.lwxf.industry4.webapp.domain.dto.company;

import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyCertificates;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/8/20 10:39
 * @Description:
 */
public class CompanyCertificatesDto extends CompanyCertificates {
    private List<UploadFiles> filesList;//附件信息
    private String fileIds;//附件id 逗号分隔

    public List<UploadFiles> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<UploadFiles> filesList) {
        this.filesList = filesList;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }
}
