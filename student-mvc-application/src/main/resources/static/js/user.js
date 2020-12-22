$(document).ready(function() {
     $("#users").click(function() {
            $.ajax({
                type : "GET",
                contentType : "application/json",
                url : "/users",
                contentType: "application/json; charset=utf-8",
                dataType : 'json',
                success : function(response) {
                    debugger
                },
                error : function(errorResponse) {
                    debugger
                }
            });
      });
});