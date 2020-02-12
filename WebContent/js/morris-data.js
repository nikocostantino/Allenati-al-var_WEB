$(function() {
	var v;
	$.ajax({
			type: "GET",
			url: "gestoreStatistiche",
			data: {ultimi10Voti : "ultimi10Voti"},
			success: function(data){
				v=data.split(" ");
				for(i=0; i<10; i++)
				{
					if(v[i]==-1)
					{
						v[i]=null;
					}
				}
				
    Morris.Area({
        element: 'morris-area-chart',
        data: [{
            y: '1 di 10',
            voto: v[0]
        }, {
            y: '2 di 10',
            voto: v[1]
        }, { 
            y: '3 di 10',
            voto: v[2]
        }, {
            y: '4 di 10',
            voto: v[3]
        }, {
            y: '5 di 10',
            voto: v[4]
        }, {
            y: '6 di 10',
            voto: v[5]
        }, {
            y: '7 di 10',
            voto: v[6]
        }, {
            y: '8 di 10',
            voto: v[7]
        }, {
            y: '9 di 10',
            voto: v[8]
        }, {
            y: '10 di 10',
            voto: v[9]
        }],
        xkey: 'y',
        ykeys: ['voto'],
        labels: ['voto'],
        pointSize: 1,
        hideHover: 'auto',
        resize: true,
        parseTime: false
    });
			}
    	}
);  


    	var v;
    	$.ajax({
    			type: "GET",
    			url: "gestoreStatistiche",
    			data: {voti : "voti"},
    			success: function(data){
    				v=data.split(" ");

    				
    				 Morris.Donut({
    				        element: 'morris-donut-chart',
    				        data: [{
    				            label: "voto 0",
    				            value: v[0]
    				        },
    				        {
    				            label: "voto 1",
    				            value: v[1]
    				        },
    				        {
    				            label: "voto 2",
    				            value: v[2]
    				        },
    				        {
    				            label: "voto 3",
    				            value: v[3]
    				        },
    				        {
    				            label: "voto 4",
    				            value: v[4]
    				        }, {
    				            label: "voto 5",
    				            value: v[5]
    				        }, {
    				            label: "voto 6",
    				            value: v[6]
    				        },
    				        {
    				            label: "voto 7",
    				            value: v[7]
    				        },
    				        {
    				            label: "voto 8",
    				            value: v[8]
    				        },
    				        {
    				            label: "voto 9",
    				            value: v[9]
    				        },
    				        {
    				            label: "voto 10",
    				            value: v[10]
    				        }],
    				        resize: true
    				    });
    			}
    		}
    	);  
    	

    	Morris.Donut({
	        element: 'morris-donut-chart2',
	        data: [{
	            label: "Corrette",
	            value: 25
	        },
	        {
	            label: "Sbagliate",
	            value: 75
	        }],
	       
	        colors: [
	          '#4CC417',
	          '#F62817'
	        ],
	        resize: true
	    });	
    	
    Morris.Bar({
        element: 'morris-bar-chart1',
        data: [{
            y: 'Rigore',
            a: 100
        }, {
            y: 'Rosso',
            a: 75
        }, {
            y: 'Giallo',
            a: 50
        }, {
            y: 'Fuorigioco',
            a: 75
        }, {
            y: 'Fallo di mano',
            a: 50
        }, {
            y: 'Goal',
            a: 100
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
    
    Morris.Bar({
        element: 'morris-bar-chart2',
        data: [{
            y: 'Normale',
            a: 100
        }, {
            y: 'Difficile',
            a: 75
        }, {
            y: 'Facile',
            a: 50
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

});
