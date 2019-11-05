<?php

use Illuminate\Database\Seeder;

class ThemLuotChoiSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
       DB::table('luot_choi')->insert([
           'nguoi_choi_id'=>1,
           'so_cau'=>1,
           'diem'=>'2000',
           'ngay_gio'=>'1:20'
       ]);
    }
}
