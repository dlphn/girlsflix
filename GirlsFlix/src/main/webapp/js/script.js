
// at page load time
$(function(){
	
	$('button[data-role="search"]').click(function() {
    	event.preventDefault();
    	if ($('#searchInput').val().length > 0) {
            window.location.href = '/GirlsFlix/series?search=' + $('#searchInput').val();
    	} else {
            window.location.href = '/GirlsFlix/series';
    	}
    });
	
	$('select[id="selectGenre"]').change(function() {
    	event.preventDefault();
    	if ($("#selectGenre").val().length > 0) {
    		var encoded = $("#selectGenre").val().replace(/&/g, 'and');
            window.location.href = '/GirlsFlix/series?genre=' + encoded;
    	} else {
            window.location.href = '/GirlsFlix/series';
    	}
    });
    
});


