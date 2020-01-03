@extends('partials.layout')

@section('page_content')
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
      <div class="x_panel">
        <div class="x_title">
          <h2>
            @if(isset($quanTriVien))
                Cập Nhật Quản Trị Viên
            @else
                Thêm Quản Trị Viên
            @endif
            </h2>
          <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
              <ul class="dropdown-menu" role="menu">
               <!-- Setting -->
              </ul>
            </li>
            <li><a class="close-link"><i class="fa fa-close"></i></a>
            </li>
          </ul>
          <div class="clearfix"></div>
        </div>
        <div class="x_content">
          <br />

        @if(isset($quanTriVien))
        <form action="{{ route('quan_tri_vien.xu_li_cap_nhat',['id'=>$quanTriVien->id]) }}" method="POST" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
        @else
        <form action="{{ route('quan_tri_vien.xu_li_them') }}" method="POST" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
        @endif
        
            @csrf
            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ten_dang_nhap">Tên Đăng Nhập: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="ten_dang_nhap" type="text" id="ten_dang_nhap" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($quanTriVien))
                    value="{{$quanTriVien->ten_dang_nhap}}"
                @endif 
                >
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="mat_khau">Mật Khẩu: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="mat_khau" type="password" id="mat_khau" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($quanTriVien))
                    value="{{$quanTriVien->mat_khau}}"
                @endif 
                >
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ho_ten">Họ Tên:: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="ho_ten" type="text" id="ho_ten" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($quanTriVien))
                    value="{{$quanTriVien->ho_ten}}"
                @endif 
                >
              </div>
            </div>

          
            <div class="ln_solid"></div>
            <div class="form-group">
              <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                <a href="{{ route('nguoi_choi.danh_sach') }}" class="btn btn-danger" type="button">Hủy</a>
                @if(!isset($quanTriVien))
                  <a href="" class="btn btn-primary" type="reset">Clear</a>
                @endif
                <button type="submit" class="btn btn-success">
                    @if(isset($quanTriVien))
                        Cập Nhật
                    @else
                        Thêm
                    @endif
                </button>
              </div>
            </div>

          </form>
        </div>
      </div>
    </div>
  </div>
@endsection