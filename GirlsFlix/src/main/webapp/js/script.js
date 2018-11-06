
// at page load time
$(function(){
	
	function filter() {
		var searchInput = $('#searchInput').val();
		var selectGenre = $("#selectGenre").val().replace(/&/g, 'and');
		if (searchInput.length > 0 || selectGenre.length > 0) {
			window.location.href = '/GirlsFlix/series?search=' + searchInput + '&genre=' + selectGenre;
		} else {
			window.location.href = '/GirlsFlix/series';
		}
	}
	
	$('button[data-role="search"]').click(function() {
    	event.preventDefault();
    	filter();
    });
	
	$('select[id="selectGenre"]').change(function() {
    	event.preventDefault();
    	filter();
    });
    
});


