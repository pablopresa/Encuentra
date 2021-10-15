'use strict';
$(document).ready(function() {
    $('#d_week').datepicker({
        daysOfWeekDisabled: "2"
    });

    $('#d_highlight').datepicker({
        daysOfWeekHighlighted: "1"
    });

    $('#d_auto').datepicker({
    	format: 'dd/mm/yyyy 00:00:00',
        autoclose: true,
    }).datepicker("setDate", new Date());

    $('#d_disable').datepicker({
        datesDisabled: ['10/15/2018', '10/16/2018' ,  '10/17/2018' , '10/18/2018' ]
    });

    $('#d_toggle').datepicker({
        keyboardNavigation: false,
        forceParse: false,
        toggleActive: true
    });

    $('#d_today').datepicker({
        keyboardNavigation: false,
        forceParse: false,
        todayHighlight: true
    });

    $('#disp_week').datepicker({
        calendarWeeks: true
    });

    $('#datepicker_range').datepicker({
    	format: 'yyyy/mm/dd 00:00:00',
    });
});
