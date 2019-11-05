<!DOCTYPE html>
<html lang="en">
  <head>
    @include('partials.header')
    @yield('css')
  <head>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">

        @include('partials.left_sidebar')
      

        @include('partials.top_navigation')

        <!-- page content -->
        @include('partials.page_content')
        <!-- /page content -->

        <!-- footer content -->
        @include('partials.footer')
        <!-- /footer content -->
      </div>
    </div>

    @include('partials.javascript')
    @yield('js')
  </body>
</html>
