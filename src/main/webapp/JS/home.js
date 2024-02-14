/**
 * 
 */

  
  
    function changeBackground(imageUrl) {
    var scroll = document.getElementById('scroll-container');
    if (scroll) {
        scroll.style.backgroundImage = 'url(' + imageUrl + ')';
    }
    }
    
   /* function defaultBackground(defaultImage){
		var scroll=document.getElementById('scroll-container');
		if(scroll){
			scroll.style.backgroundImage ='url("images/container.webp")';
		}
	}*/
    
      function playVideo(frameId, overlayId, videoUrl) {
        var frame = document.getElementById(frameId);
        var overlay = document.getElementById(overlayId);
        
        if (frame && overlay) {
            frame.src = videoUrl
            overlay.style.display = 'block';
        }
    }
     function closeVideo(frameId, overlayId) {
        var frame = document.getElementById(frameId);
        var overlay = document.getElementById(overlayId);

        if (frame && overlay) {
            frame.src = '';
            overlay.style.display = 'none';
        }
    }
    document.getElementById('login').addEventListener('click', function() {
     
      document.getElementById('login').style.display = 'none';
      document.getElementById('logout').style.display = 'block';
    });

    document.getElementById('logout').addEventListener('click', function() {
      
      document.getElementById('login').style.display = 'block';
      document.getElementById('logout').style.display = 'none';
    });
 