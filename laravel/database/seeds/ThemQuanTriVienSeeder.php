<?php

use Illuminate\Database\Seeder;

class ThemQuanTriVienSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('quan_tri_vien')->insert([
            ['ten_dang_nhap'=>'admin',
            'mat_khau'=>'123',
            'ho_ten'=>'Nguyễn Văn Khương'
            ]

        ]);
    }
}
