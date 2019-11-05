@extends('partials.layout')
@section('page_content')
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
      <div class="x_panel">
        <div class="x_title">
          <h2>
            @if(isset($cauHoi))
                Cập Nhật Câu Hỏi
            @else
                Thêm Câu Hỏi
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

        @if(isset($cauHoi))
        <form action="{{ route('cau_hoi.xu_li_cap_nhat',['id'=>$cauHoi->id]) }}" method="POST" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
        @else
        <form action="{{ route('cau_hoi.xu_li_them') }}" method="POST" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
        @endif
        
            @csrf
            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="noi_dung">Nội Dung: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="noi_dung" type="text" id="noi_dung" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHoi))
                    value="{{$cauHoi->noi_dung}}"
                @endif 
                >
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="linh_vuc_id">Lĩnh Vực: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="linh_vuc_id" type="number" id="linh_vuc_id" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHoi))
                    value="{{$cauHoi->linh_vuc_id}}"
                @endif 
                >
              </div>
            </div>


            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phuong_an_a">Phương Án A: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="phuong_an_a" type="text" id="phuong_an_a" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHoi))
                    value="{{$cauHoi->phuong_an_a}}"
                @endif 
                >
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phuong_an_b">Phương Án B: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="phuong_an_b" type="text" id="phuong_an_b" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHoi))
                    value="{{$cauHoi->phuong_an_b}}"
                @endif 
                >
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phuong_an_c">Phương Án C: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="phuong_an_c" type="text" id="phuong_an_c" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHoi))
                    value="{{$cauHoi->phuong_an_c}}"
                @endif 
                >
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phuong_an_d">Phương Án D: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="phuong_an_d" type="text" id="phuong_an_d" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHoi))
                    value="{{$cauHoi->phuong_an_d}}"
                @endif 
                >
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="dap_an">Đáp Án: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="dap_an" type="text" id="dap_an" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($cauHoi))
                    value="{{$cauHoi->dap_an}}"
                @endif 
                >
              </div>
            </div>
           
            <div class="ln_solid"></div>
            <div class="form-group">
              <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                <a href="{{ route('cau_hoi.danh_sach') }}" class="btn btn-danger" type="button">Hủy</a>
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