domok = document.getElementById;

offset_heure=0;
text = "text1";


if (domok)
{
	if(navigator.appName.substring(0,3) == "Net")
	document.captureEvents(Event.MOUSEMOVE);
}



function get_mouse(e)
{
	var x = (navigator.appName.substring(0,3) == "Net") ? e.pageX : event.x+document.body.scrollLeft;
	var y = (navigator.appName.substring(0,3) == "Net") ? e.pageY : event.y+document.body.scrollTop;
	var affiche = minutesToHours(y+(offset_heure - 93));
		
	document.getElementById(text).value = affiche;
}

function change_textfield()
{
	if(text == "text2")
	{
		text = "fin";
	}
	else
	{
		if(text == "text1")
			text = "text2";
	}
}

function minutesToHours(min)
{
  var hrs = Math.floor(min/60);
  min = min % 61;
  if(min<10) min = "0" + min;
  return hrs+":"+min;
}


function setOffset(nb)
{
	offset_heure=nb;
}

function messageWindow(url,name, width, height)
{
  var left = (screen.width/2) - width/2;
  var top = (screen.height/2) - height/2;
  var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbar=no,resizable=no,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;
  name = window.open(url,name, styleStr);
}

var popup;

