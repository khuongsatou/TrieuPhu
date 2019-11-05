<?php

use Illuminate\Database\Seeder;

class ThemCauHoiSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('cau_hoi')->insert([
            ['noi_dung'=>'Đây là câu hỏi?',
            'linh_vuc_id'=>1,
            'phuong_an_a'=>'Hỏi A',
            'phuong_an_b'=>'Hỏi B',
            'phuong_an_c'=>'Hỏi C',
            'phuong_an_d'=>'Hỏi D',
            'dap_an'=>'D'
            ]
        ]);
    }
}
