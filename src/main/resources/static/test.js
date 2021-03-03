/**
 * 
 */

<script type='text/javascript'>
// define vars
var url = 'http://localhost:8080/login';

// ajax call
$.ajax({
    url: url,
    dataType : 'jsonp',
    beforeSend : function(xhr) {
      // set header if JWT is set
      if ($window.sessionStorage.token) {
          xhr.setRequestHeader("Authorization", "Bearer " +  $window.sessionStorage.token);
      }

    },
    error : function() {
      // error handler
    },
    success: function(data) {
        // success handler
    }
});
</script>