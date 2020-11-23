let dom = document.createElement('div')
dom.className = 'mtips'
dom.setAttribute('id', 'mtips')
document.getElementsByTagName('body')[0].appendChild(dom)

var mtips = function() {


	function tipsShow(c, t) {

		let content = (c == undefined ? '' : c)
		let time = (t == undefined || t === 0 ? 3000 : t)
		let cd = document.createElement('div')
		cd.className = 'tips'

		cd.innerHTML += '<div class="tips-nav"><span class="tips-show">' + content + '</span></div>';
		dom.appendChild(cd)
		clearDomChild(time, 'tips')

	}



	function successShow(c, t) {
		let content = (c == undefined ? '成功' : c)
		let time = (t == undefined || t === 0 ? 3600 : t)
		let cd = document.createElement('div')
		cd.className = 'success'
		cd.onclick=function(){
			clearDomChild(0, 'success')
		}
		cd.innerHTML += '<div class="success-nav"><div class="ic"></div><p class="title">'+content+'</p></div>';
		dom.appendChild(cd)
		clearDomChild(time, 'success')
	}
	
	function errorShow (c, t) {
		let content = (c == undefined ? '出错了' : c)
		let time = (t == undefined || t === 0 ? 3600 : t)
		let cd = document.createElement('div')
		cd.className = 'error'
		cd.onclick=function(){
			clearDomChild(0, 'error')
		}
		cd.innerHTML += '<div class="error-nav"><div class="ic"></div><p class="title">'+content+'</p></div>';
		dom.appendChild(cd)
		clearDomChild(time, 'error')
	}


	function loadingShow(c, t) {
		let html = '<div class="loading"><div class="load"></div></div>';
		dom.innerHTML += html
	}




	function clearloadingShow() {
		let Child = document.querySelector('.mtips .loading')
		dom.removeChild(Child)
	}

	function clearDomChild(t, d) {

		let Child = document.querySelector('.mtips .' + d + '')

		 setTimeout(function() {
		 	dom.innerHTML=''
		 }, t)


	}






	return {

		tips: function(c, t) {
			tipsShow(c, t)
		},

		success: function(c, t) {
			successShow(c, t)
		},

		error: function(c, t) {
errorShow(c, t)
		},

		warning: function(c, t) {

		},

		loading: function(c, t) {
			loadingShow(c, t)
		},

		clearloading: function() {
			clearloadingShow()
		},

	}

}()
