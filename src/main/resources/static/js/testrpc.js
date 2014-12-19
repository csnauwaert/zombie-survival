(function(){
    game = {
        init: function(){
            var self = this;
            $(document).ready(function(){
                self.start();
            });
        },
        start: function(){
            var self = this;
            console.log('widget started');
            $('.number_clicked')[0].innerHTML = 0;
            $('.content')[0].innerHTML = '';
            $('.testcall_rpc').click(function(){
                console.log('button pressed');
                var name = $('input').val();
                if (name.length == 0) {
                    name = '';
                }
                console.log('name = '+name);
                $.ajax({url: 'http://localhost:8080/testrpc?name='+name}).then(function(data, status, jqxhr) {
                    $('.number_clicked')[0].innerHTML = data.id;
                    $('.content')[0].innerHTML = data.name;
                    console.log(jqxhr);
                });
            });
        },
    };
})();
