<?php

use Illuminate\Database\Seeder;

class ThemNguoiChoiSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('nguoi_choi')->insert([
            ['ten_dang_nhap' => 'admin', 'mat_khau' => '123', 'email' => 'yoshi240499@gmail.com', 'hinh_dai_dien' => '1.jpg', 'diem_cao_nhat' => 123, 'credit' => 100 ],
            ['ten_dang_nhap' => 'user', 'mat_khau' => '123', 'email' => 'yoshi240499@gmail.com', 'hinh_dai_dien' => '2.jpg', 'diem_cao_nhat' => 30, 'credit' => 200 ]
            ]);


    }
}
