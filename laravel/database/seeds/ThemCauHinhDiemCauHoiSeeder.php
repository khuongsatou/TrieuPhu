<?php

use Illuminate\Database\Seeder;

class ThemCauHinhDiemCauHoiSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('cau_hinh_diem_cau_hoi')->insert([
            ['thu_tu'=>1,
            'diem'=>2
            ]
        ]);
    }
}
