$(document).ready(function() {
	var cb1 = document.querySelector('.mycard thead').querySelector('input');
	var cbs = document.querySelector('.mycard tbody').getElementsByTagName('input');
	var trs = document.querySelector('.mycard tbody').getElementsByTagName('tr');
	var spans = document.querySelector('.buy').querySelectorAll('span');
	var num = 0;
	var money = 0;
	// ��һ�鹦�ܣ�һ����ѡ�����ȫѡ��ȫ��ѡ��
	cb1.onclick = function() {

		for(var i = 0; i < cbs.length; i++) {
			cbs[i].checked = cb1.checked;
		}

		if(cb1.checked) {
			num = cbs.length;
			addmon();

		} else {
			num = 0;
			money = 0;
		}
		spans[1].innerHTML = num;
		spans[3].innerHTML = money;

	}

	// �ڶ��鹦�ܣ����浥�����Ӱ���ܰ�ť��
	for(var i = 0; i < cbs.length; i++) {
		cbs[i].onclick = function() {
			var flag = true;
			for(var i = 0; i < cbs.length; i++) {
				if(cbs[i].checked == false) {
					flag = false;
				}
			}
			cb1.checked = flag;
			if(cb1.checked) {
				num = cbs.length;
				addmon();
			} else {
				num = 0;
				money = 0;
				for(var i = 0; i < cbs.length; i++) {
					if(cbs[i].checked == true) {
						num++;
						var a = trs[i].querySelectorAll('td')[3].innerHTML;
						var b = Number(a);
						money += b;
					}
				}
			}
			spans[1].innerHTML = num;
			spans[3].innerHTML = money;
		}

	}

	function addmon() {
		money = 0;
		for(var i = 0; i < trs.length; i++) {
			//console.log(i);
			//console.log(trs[i].querySelectorAll('td')[3]);

			var a = trs[i].querySelectorAll('td')[3].innerHTML;
			var b = Number(a);
			money += b;
		}
	}

});