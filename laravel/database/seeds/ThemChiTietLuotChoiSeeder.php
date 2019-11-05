<?php

use Illuminate\Database\Seeder;

class ThemChiTietLuotChoiSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('chi_tiet_luot_choi')->insert([
            ['luot_choi_id'=>1,
            'cau_hoi_id'=>1,
            'phuong_an'=>'A',
            'diem'=>50
            ]
        ]);
    }
}
