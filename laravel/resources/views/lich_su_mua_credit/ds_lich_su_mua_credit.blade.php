@extends('partials.layout')
@section('css')

   
    <!-- Datatables -->
    <link href="{{asset('../vendors/datatables.net-bs/css/dataTables.bootstrap.min.css') }}" rel="stylesheet">
    <link href="{{asset('../vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css') }}" rel="stylesheet">
    <link href="{{asset('../vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css') }}" rel="stylesheet">
    <link href="{{asset('../vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css') }}" rel="stylesheet">
    <link href="{{asset('../vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css') }}" rel="stylesheet">


@endsection


@section('js')
    <!-- Datatables -->
    <script src="{{asset('../vendors/datatables.net/js/jquery.dataTables.min.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-bs/js/dataTables.bootstrap.min.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-buttons/js/dataTables.buttons.min.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-buttons/js/buttons.flash.min.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-buttons/js/buttons.html5.min.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-buttons/js/buttons.print.min.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-keytable/js/dataTables.keyTable.min.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-responsive/js/dataTables.responsive.min.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js') }}"></script>
    <script src="{{asset('../vendors/datatables.net-scroller/js/dataTables.scroller.min.js') }}"></script>
    <script src="{{asset('../vendors/jszip/dist/jszip.min.js') }}"></script>
    <script src="{{asset('../vendors/pdfmake/build/pdfmake.min.js') }}"></script>
    <script src="{{asset('../vendors/pdfmake/build/vfs_fonts.js') }}"></script>

@endsection

@section('page_content')
<div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Danh Sách Lịch sử mua Credit</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                      </li>
                      <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                        <ul class="dropdown-menu" role="menu">
                          <!-- setting -->
                        </ul>
                      </li>
                      <li><a class="close-link"><i class="fa fa-close"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_support">
                  <a href="{{ route('lich_su_mua_credit.them')}}" style="background-color:#337ab7;color:#fff;border-color:#2e6da4" class="btn btn-app">
                          <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Thêm Mới
                      </a>
                      <a href="" style="background-color:#4B5F71;color:#fff;border-color:#364B5F" class="btn btn-app">
                          <i class="fa fa-repeat"></i></span> Phục Hồi
                      </a>
                      <a href="" style="background-color:#d9534f;color:#fff;border-color:#d43f3a" class="btn btn-app">
                          <i class="fa fa-edit"></i></span> Lập Báo Cáo
                      </a>
                  </div>
                  <div class="x_content">
                    
                    <table id="datatable" class="table table-striped table-bordered">
                      <thead>
                        <tr>
                          <th>ID</th>
                          @if(isset($nguoiChoi))
                          <th>Tên Đăng Nhập</th>
                          @endif
                          <th>Credit</th>
                          <th>Số Tiền</th>
                          <th></th>
                  
                        </tr>
                      </thead>


                      <tbody>
                        @foreach ($lichSuMuaCredits as $item)
                        <tr>
                          <td>{{ $item->id }}</td>
                          @if(isset($nguoiChoi))
                          <td>{{ $nguoiChoi->ten_dang_nhap }}</td>
                          @endif
                          <td>{{ $item->credit }}</td>
                          <td>{{ $item->so_tien }}</td>
                          <td>
                            <a href="{{ route('lich_su_mua_credit.cap_nhat',['id' => $item->id]) }}" type="button" class="btn btn-round btn-primary"><i class="fa fa-pencil"></i></a>
                            
                            <a href="{{ route('lich_su_mua_credit.xoa',['id' => $item->id]) }}" type="button" class="btn btn-round btn-danger"><i class="fa fa-trash-o"></i></a>
                            </td>
                        </tr>
                        @endforeach
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>

@endsection