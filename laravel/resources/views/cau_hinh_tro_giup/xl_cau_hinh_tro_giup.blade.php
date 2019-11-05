@extends('partials.layout')
@section('page_content')
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
      <div class="x_panel">
        <div class="x_title">
          <h2>
            @if(isset($cauHinhTroGiup))
                Cập Nhật Cấu Hình Trợ Giúp
            @else
                Thêm Cấu Hình Trợ Giúp
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

        @if(isset($cauHinhTroGiup))
        <form action="{{ route('cau_hinh_tro_giup.xu_li_cap_nhat',['id'=>$cauHinhTroGiup->id]) }}" method="POST" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
        @else
        <form action="{{ route('cau_hinh_tro_giup.xu_li_them') }}" method="POST" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
        @endif
        
            @csrf
            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="loai_tro_giup">Loại trợ giúp: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="loai_tro_giup" type="text" id="loai_tro_giup" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHinhTroGiup))
                    value="{{$cauHinhTroGiup->loai_tro_giup}}"
                @endif 
                >
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="thu_tu">Thứ Tự: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="thu_tu" type="number" id="thu_tu" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHinhTroGiup))
                    value="{{$cauHinhTroGiup->thu_tu}}"
                @endif 
                >
              </div>
            </div>


            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="credit">Credit: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="credit" type="text" id="credit" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHinhTroGiup))
                    value="{{$cauHinhTroGiup->credit}}"
                @endif 
                >
              </div>
            </div>

           
           
            <div class="ln_solid"></div>
            <div class="form-group">
              <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                <a href="{{ route('cau_hinh_tro_giup.danh_sach') }}" class="btn btn-danger" type="button">Hủy</a>
                @if(!isset($cauHinhTroGiup))
                  <a href="" class="btn btn-primary" type="reset">Clear</a>
                @endif
                <button type="submit" class="btn btn-success">
                    @if(isset($cauHinhTroGiup))
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