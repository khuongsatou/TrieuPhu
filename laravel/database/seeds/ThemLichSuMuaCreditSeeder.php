<?php

use Illuminate\Database\Seeder;

class ThemLichSuMuaCreditSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('lich_su_mua_credit')->insert([
            ['nguoi_choi_id'=>1,
            'goi_credit_id'=>1,
            'credit'=>200,
            'so_tien'=>50
            ]
        ]);
    }
}
