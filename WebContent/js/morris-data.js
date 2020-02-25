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
    	
    	
    	

});
