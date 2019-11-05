<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        // $this->call(UsersTableSeeder::class);
        //Các bảng không tham chiếu
        $this->call(ThemCauHinhDiemCauHoiSeeder::class);
        $this->call(ThemCauHinhAppSeeder::class);
        $this->call(ThemQuanTriVienSeeder::class);
        $this->call(ThemCauHinhTroGiupSeeder::class);

        //các bảng có quan hệ khóa chính ưu tiên
        $this->call(ThemLinhVucSeeder::class);
        $this->call(ThemNguoiChoiSeeder::class);
        $this->call(ThemCauHinhAppSeeder::class);
        $this->call(ThemGoiCreditSeeder::class);

        //Các bảng khóa ngoại
        $this->call(ThemCauHoiSeeder::class);
        $this->call(ThemLuotChoiSeeder::class);

        //cá bảng thực thể yếu
        $this->call(ThemChiTietLuotChoiSeeder::class);
        $this->call(ThemLichSuMuaCreditSeeder::class);


    }
}
