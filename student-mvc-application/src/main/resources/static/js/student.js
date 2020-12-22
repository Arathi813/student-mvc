$(document).ready(function() {
    $(".alert-banner").fadeTo(2000, 500).slideUp(500, function() {
        $("#success-alert").slideUp(500);
    });

    $(".new-student").click(function() {
        $("#createStudentSubmit").removeClass("hidden");
    });

    $("#createStudentSubmit").click(function() {
        var data =  {
                   "firstName": $('#newForm').find('input[name="firstName"]').val(),
                   "lastName": "",
                   "batch": "",
                   "email": $('#newForm').find('input[name="email"]').val()
               }
        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/student",
            contentType: "application/json; charset=utf-8",
            data : JSON.stringify(data),
            dataType : 'json',
            success : function(response) {
                window.location.href = "/student/list?message=created";
            },
            error : function(errorResponse) {
                if(errorResponse.responseJSON.code == "NO_VALID_REQUEST")
                $("#notification").text("Please fill valid details").addClass("alert alert-danger alert-banner");
            }
        });
    });


    $("#editStudent").click(function() {
        var id = $(this).prop("value")
        debugger
         $.ajax({
                type : "GET",
                contentType : "application/json",
                url : "/student/"+id,
                contentType: "application/json; charset=utf-8",
                dataType : 'json',
                success : function(response) {
                    $('#newModal').modal('show');
                    $("#firstName").val(response.firstName);
                    $("#email").val(response.email);
                    $("#studentId").val(response.id)
                    $("#modalName").text("Edit Student");
                    $("#editStudentSubmit").removeClass("hidden");
                },
                error :
                function(errorResponse) {
                    if(errorResponse.responseJSON.code == "NO_VALID_REQUEST")
                    $("#notification").text("Please fill valid details").addClass("alert alert-danger alert-banner");
                }
         });
    });

    $("#editStudentSubmit").click(function() {
            debugger
            var id = $("#studentId").val();
            var data =  {
                       "id": id,
                       "firstName": $('#newForm').find('input[name="firstName"]').val(),
                       "lastName": "",
                       "batch": "",
                       "email": $('#newForm').find('input[name="email"]').val()
                   }
            $.ajax({
                type : "PUT",
                contentType : "application/json",
                url : "/student/"+id,
                contentType: "application/json; charset=utf-8",
                data : JSON.stringify(data),
                dataType : 'json',
                success : function(response) {
                    window.location.href = "/student/list?message=updated";
                },
                error : function(errorResponse) {
                    if(errorResponse.responseJSON.code == "NO_VALID_REQUEST")
                    $("#notification").text("Please fill valid details").addClass("alert alert-danger alert-banner");
                }
            });
     });



});

