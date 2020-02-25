$(function() {
    	
    	var v;
    	$.ajax({
    			type: "GET",
    			url: "gestoreStatistiche",
    			data: {votiTotali : "votiTotali"},
    			success: function(data){
    				v=data.split(" ");

    	Morris.Donut({
	        element: 'morris-donut-chart2',
	        data: [{
	            label: "Corrette",
	            value: v[0],
	            formatted: v[0]+'%'
	        },
	        {
	            label: "Sbagliate",
	            value: v[1],
	            formatted: v[1]+'%'

	        }],
	        
	        formatter: function(x,data){return data.formatted;},
	        
	        colors: [
	          '#4CC417',
	          '#F62817'
	        ],
	        resize: true
	    });	
    			}
    	});
    	
    	
    	
    	
    	
    	
    	var v;
    	$.ajax({
    			type: "GET",
    			url: "gestoreStatistiche",
    			data: {videoCategorie : "videoCategorie"},
    			success: function(data){
    				v=data.split(" ");
    	
    Morris.Bar({
        element: 'morris-bar-chart1',
        data: [{
            y: 'Rigore',
            a: v[0]
        }, {
            y: 'Rosso',
            a: v[1]
        }, {
            y: 'Giallo',
            a: v[2]
        }, {
            y: 'Fuorigioco',
            a: v[3]
        }, {
            y: 'Fallo di mano',
            a: v[4]
        }, {
            y: 'Goal',
            a: v[5]
        }],
        xkey: 'y',
        ykeys: ['a'],
        labels: ['Video presenti'],
        hideHover: 'auto',
        barColors: function (row, series, type) {
        	console.log("--> "+row.label, series, type);
        	if(row.label == "Rigore") return "#151B54";
        	else if(row.label == "Rosso") return "#151B8D";
        	else if(row.label == "Giallo") return "#2554C7";
        	else if(row.label == "Fuorigioco") return "#2B60DE";
        	else if(row.label == "Fallo di mano") return "#3090C7";
        	else if(row.label == "Goal") return "#3BB9FF";
        	},
        resize: true
    });
    			}
    	});
    
    	
    	var v;
    	$.ajax({
    			type: "GET",
    			url: "gestoreStatistiche",
    			data: {videoDifficolta : "videoDifficolta"},
    			success: function(data){
    				v=data.split(" ");
    Morris.Bar({
        element: 'morris-bar-chart2',
        data: [{
            y: 'Normale',
            a: v[0]
        }, {
            y: 'Difficile',
            a: v[1]
        }, {
            y: 'Facile',
            a: v[2]
        }],
        xkey: 'y',
        ykeys: ['a'],
        labels: ['Video presenti'],
        hideHover: 'auto',
        barColors: function (row, series, type) {
        	console.log("--> "+row.label, series, type);
        	if(row.label == "Normale") return "#FFFF00";
        	else if(row.label == "Difficile") return "#FF0000";
        	else if(row.label == "Facile") return "#00FF00";
        	},
        resize: true
    });
    			}
    	});

});
