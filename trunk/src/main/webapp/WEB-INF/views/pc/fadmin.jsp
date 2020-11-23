<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!DOCTYPE html><html><head><meta charset="utf-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>欢迎登录掌上管家PC版</title><link rel="stylesheet" type="text/css" href="layui/css/layui.css"><script src="layui/layui.js" type="text/javascript" charset="utf-8"></script> <%@ include file="/WEB-INF/views/common/common_header.jsp"%> <style type="text/css">* {
				margin: 0px;
				padding: 0px;
				font-family: "微软雅黑";
				box-sizing: border-box;
			}
			
			ul,
			ol {
				list-style: none;
			}</style><link href="/css/fadmin.f2b3fc396c5db53394855e0037bbfd2c.css" rel="stylesheet"></head><body style="background: #f0f2f5;"><div id="app"></div><script type="text/javascript">window._CONFIG = {};
			<!--外网服务器-->
			window._CONFIG['websocketDomainURL'] = 'wss://erp4api.hnlwxf.com';

			document.onkeydown = function(e) {

				if(e.keyCode == 9) {
					if(e.preventDefault) {
						e.preventDefault();
					} else {
						e.returnValue = false;
					}
				}
			}

			var cacse = document.getElementById("lwxf_preload")

			if(cacse) {
				var pfcfg = cacse.innerText.replace(/_e@gt@e_/g, '>').replace(/_e@lt@e_/g, '<')
				var cfg = JSON.parse(pfcfg.trim())

				window.lwxfPreload = cfg

			}</script><script type="text/javascript" src="/js/manifest.0be32e18e676bd2f629e.js"></script><script type="text/javascript" src="/js/fadmin.f8eff132386d5df7a63f.js"></script></body></html>