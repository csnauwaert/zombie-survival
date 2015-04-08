(function(){
    $(document).ready(function(){
        $('input[name=username]').focus();
        $('#create_user').submit(function(){
            $('#login_already_taken').addClass('hidden');
            $('#invalid_field').addClass('hidden');
            $('input').removeClass('invalid');
            var username = $('input[name=username]');
            var password = $('input[name=password]');
            var csrf = $('input[name=_csrf]');
            var error = false;
            if (username.val().length === 0){
                $('#invalid_field').removeClass('hidden');
                username.addClass('invalid');
                error = true;
            }
            if (password.val().length === 0){
                $('#invalid_field').removeClass('hidden');
                password.addClass('invalid');
                error = true;
            }
            if (error === false) {
                $.ajax({
                    type: 'POST',
                    url: '/register_user',
                    data: {
                        username: username.val(),
                        password: password.val(),
                        _csrf: csrf.val(),
                    },
                    success: function(data){
                        window.location.href = "/create_user?success";
                    },
                    error: function (xhr, ajaxOptions, error) {
                        if (error === 'Conflict'){
                            $('#login_already_taken').removeClass('hidden');
                        }
                        else{
                            window.location.href = "/error";
                        }
                    },
                });
            }
            return false;
        });
    });
})();
