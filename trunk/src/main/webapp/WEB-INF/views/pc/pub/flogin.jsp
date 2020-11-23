<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <!DOCTYPE html><html><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>欢迎登录掌上管家PC版</title> <%@ include file="/WEB-INF/views/common/common_header.jsp"%> <style type="text/css">*{
				margin: 0px;
				padding: 0px;
				/* font-family: "微软雅黑"; */
				box-sizing: border-box;
			}
			
			ul,ol{
				list-style: none;
			}</style><link href="/css/flogin.f32b95a062e145941cd67d78ee410194.css" rel="stylesheet"></head><body style="background: #f5f7f9;"><div id="flogin"></div><script type="text/javascript">var locationhostname=window.location.hostname

			if(locationhostname=='hterp.htjjsc.com'){
				document.title='欢迎登录-红田家居'
			}else if(locationhostname=='erp4api.hnlwxf.com'){
				document.title='欢迎登录-老屋新房'
			}else if(locationhostname=='test.hnlwxf.com'){
				document.title='欢迎登录-老屋新房测试版'
			}else  if(locationhostname=='hebht.hnlwxf.com'){
				document.title='欢迎登录-海尔滨红田家居'
			}else{
				document.title='欢迎登录掌上管家PC版'
			}
		      
		      
		
					
					var cacse = document.getElementById("lwxf_preload")
		
					if(cacse){
						var pfcfg = cacse.innerText.replace(/_e@gt@e_/g, '>').replace(/_e@lt@e_/g, '<')
						var cfg = JSON.parse(pfcfg.trim())						
						window.lwxfPreload=cfg					
					}else{
						window.lwxfPreload={
						/* 	"nameType":0 */
						} 
				}</script><script type="text/javascript" src="/js/manifest.1766e3c7586055624ca0.js"></script><script type="text/javascript" src="/js/vendor.1b71c90e8fc637065c06.js"></script><script type="text/javascript" src="/js/flogin.ca500140384a519b0fa4.js"></script></body></html>