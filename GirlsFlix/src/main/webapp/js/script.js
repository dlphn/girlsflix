
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
    
});


