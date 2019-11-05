@extends('partials.layout')
@section('page_content')
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
      <div class="x_panel">
        <div class="x_title">
          <h2>
            @if(isset($goiCredit))
                Cập Nhật Gói Credit
            @else
                Thêm Gói Credit
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

        @if(isset($goiCredit))
        <form action="{{ route('goi_credit.xu_li_cap_nhat',['id'=>$goiCredit->id]) }}" method="POST" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
        @else
        <form action="{{ route('goi_credit.xu_li_them') }}" method="POST" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">
        @endif
        
            @csrf
            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ten_goi">Tên Gói: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="ten_goi" type="text" id="ten_goi" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($goiCredit))
                    value="{{$goiCredit->ten_goi}}"
                @endif 
                >
              </div>
            </div>

            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ten_goi">Credit: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="credit" type="text" id="credit" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($goiCredit))
                    value="{{$goiCredit->credit}}"
                @endif 
                >
              </div>
            </div>
           
            <div class="form-group">
              <label class="control-label col-md-3 col-sm-3 col-xs-12" for="so_tien">Số Tiền: <span class="required">*</span>
              </label>
              <div class="col-md-6 col-sm-6 col-xs-12">
                <input name="so_tien" type="text" id="so_tien" required="required" class="form-control col-md-7 col-xs-12" 
                @if(isset($goiCredit))
                    value="{{$goiCredit->so_tien}}"
                @endif 
                >
              </div>
            </div>
           
           
            <div class="ln_solid"></div>
            <div class="form-group">
              <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                <a href="{{ route('goi_credit.danh_sach') }}" class="btn btn-danger" type="button">Hủy</a>
                @if(!isset($goiCredit))
                  <a href="" class="btn btn-primary" type="reset">Clear</a>
                @endif
                <button type="submit" class="btn btn-success">
                    @if(isset($goiCredit))
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