@extends('partials.layout')
@section('page_content')
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
      <div class="x_panel">
        <div class="x_title">
          <h2>
            @if(isset($cauHoi))
                Cập Nhật Điểm Câu Hỏi
            @else
                Thêm Điểm Câu Hỏi
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

        @if(isset($cauHinhDiemCauHoi))
        <form action="{{ route('cau_hinh_diem_cau_hoi.xu_li_cap_nhat',['id'=>$cauHinhDiemCauHoi->id]) }}" method="POST" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
        @else
        <form action="{{ route('cau_hinh_diem_cau_hoi.xu_li_them') }}" method="POST" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
        @endif
        
            @csrf
            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="thu_tu">Thứ tự: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="thu_tu" type="text" id="thu_tu" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHinhDiemCauHoi))
                    value="{{$cauHinhDiemCauHoi->thu_tu}}"
                @endif 
                >
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="diem">Điểm: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="diem" type="number" id="diem" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHinhDiemCauHoi))
                    value="{{$cauHinhDiemCauHoi->diem}}"
                @endif 
                >
              </div>
            </div>


           
            <div class="ln_solid"></div>
            <div class="form-group">
              <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                <a href="{{ route('cau_hinh_diem_cau_hoi.danh_sach') }}" class="btn btn-danger" type="button">Hủy</a>
                @if(!isset($cauHoi))
                  <a href="" class="btn btn-primary" type="reset">Clear</a>
                @endif
                <button type="submit" class="btn btn-success">
                    @if(isset($cauHoi))
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