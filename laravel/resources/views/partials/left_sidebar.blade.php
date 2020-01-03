 <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="." class="site_title"><i class="fa fa-paw"></i> <span>DASH BOARD</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="{{ asset('images/img.jpg') }}" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                {{-- <span>Welcome,</span> --}}
                <h2>Nguyễn Văn Khương</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  <li><a href="/"><i class="fa fa-home"></i> Trang Chủ <span class="fa fa-chevron-down"></span></a>
                  </li>
                  <li><a><i class="fa fa-edit"></i> Bảng <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                    <li><a href="{{ route('linh_vuc.danh_sach') }}">Lĩnh Vực</a></li>
                      <li><a href="{{ route('nguoi_choi.danh_sach') }}">Người Chơi</a></li>
                      <li><a href="{{ route('goi_credit.danh_sach') }}">Gói Credit</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-copy"></i> Bảng Phụ <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="{{ route('cau_hoi.danh_sach') }}">Câu Hỏi</a></li>
                      <li><a href="{{ route('chi_tiet_luot_choi.danh_sach') }}">Chi Tiết Lượt Chơi</a></li>
                      <li><a href="{{ route('luot_choi.danh_sach') }}">Lượt Chơi</a></li>
                      <li><a href="{{ route('lich_su_mua_credit.danh_sach') }}">Lịch Sử Mua Credit</a></li>
                      <li><a href="{{ route('chi_tiet_luot_choi.danh_sach') }}">Chi Tiết lượt chơi</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-desktop"></i> Cấu Hình <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="{{ route('quan_tri_vien.danh_sach') }}">Quản Trị Viên</a></li>
                      <li><a href="{{ route('cau_hinh_app.danh_sach') }}">App</a></li>
                      <li><a href="{{ route('cau_hinh_tro_giup.danh_sach') }}">Trợ Giúp</a></li>
                      <li><a href="{{ route('cau_hinh_diem_cau_hoi.danh_sach') }}">Điểm Câu Hỏi</a></li>
                    </ul>
                  </li>
                  
                </ul>
              </div>
              <div class="menu_section">
                <h3>Live On</h3>
                  <li><a href="javascript:void(0)"><i class="fa fa-laptop"></i>  Về Chúng Tôi <span class="label label-success pull-right">Coming Soon</span></a></li>
                </ul>
              </div>

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="/dang_xuat">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>