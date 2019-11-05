@extends('partials.layout')

@section('page_content')
<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>Bảng Tin <small>...</small></h2>
        <ul class="nav navbar-right panel_toolbox">
          <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
          </li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="#">Settings 1</a>
              </li>
              <li><a href="#">Settings 2</a>
              </li>
            </ul>
          </li>
          <li><a class="close-link"><i class="fa fa-close"></i></a>
          </li>
        </ul>
        <div class="clearfix"></div>
      </div>
      <div class="x_content">

        <div class="bs-example" data-example-id="simple-jumbotron">
          <div class="jumbotron">
            <h1>Xin chào!</h1>
            <p> Bạn đã đăng nhập vào khu vực quản trị App "ai là triệu phú" để bắt đầu hãy xem hướng dẫn bên dưới..</p>
          </div>
        </div>

      </div>
    </div>
  </div>

  <!-- Tiếp -->
  <div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2><i class="fa fa-align-left"></i> Home / Tutorial <small>...</small></h2>
        <ul class="nav navbar-right panel_toolbox">
          <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
          </li>
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
            <ul class="dropdown-menu" role="menu">
              <li><a href="#">Settings 1</a>
              </li>
              <li><a href="#">Settings 2</a>
              </li>
            </ul>
          </li>
          <li><a class="close-link"><i class="fa fa-close"></i></a>
          </li>
        </ul>
        <div class="clearfix"></div>
      </div>
      <div class="x_content">

        <!-- start accordion -->
        <div class="accordion" id="accordion" role="tablist" aria-multiselectable="true">
          <div class="panel">
            <a class="panel-heading" role="tab" id="headingOne" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
              <h4 class="panel-title">Hãy bắt đầu #1</h4>
            </a>
            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                <div class="panel-body">
                    <a href="/cau_hinh_app"><p><strong>Cấu Hình App #1</strong></p></a>
                        Bạn có thể tùy chọn thay đổi thông số app.
                  </div>
            </div>
          </div>
          <div class="panel">
            <a class="panel-heading collapsed" role="tab" id="headingTwo" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
              <h4 class="panel-title">Các bước tiếp theo #2</h4>
            </a>
            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
              <div class="panel-body">
                <a href="/linh_vuc"><p><strong>Thêm Lĩnh Vực</strong></p></a>
                    Thêm lĩnh vực là cần thiết để tạo nên 1 hệ thống câu hỏi hóc búa
              </div>
            </div>
          </div>
          <div class="panel">
            <a class="panel-heading collapsed" role="tab" id="headingThree" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
              <h4 class="panel-title">Thao tác khác #3</h4>
            </a>
            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
              <div class="panel-body">
                <p><strong>Update</strong>
                </p>
                    Đợi tui nghĩ ra rồi thêm
              </div>
            </div>
          </div>
        </div>
        <!-- end of accordion -->


      </div>
    </div>
  </div>


@endsection
