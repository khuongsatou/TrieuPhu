<?php

use Illuminate\Database\Seeder;

class ThemCauHinhTroGiupSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('cau_hinh_tro_giup')->insert([
            ['loai_tro_giup'=>1,
            'thu_tu'=>2,
            'credit'=>1200
            ]
        ]);
    }
}
