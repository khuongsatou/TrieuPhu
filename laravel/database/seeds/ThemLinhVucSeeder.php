<?php

use Illuminate\Database\Seeder;

class ThemLinhVucSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('linh_vuc')->insert([
        ['ten_linh_vuc' => 'toán'],
        ['ten_linh_vuc' => 'lý'],
        ['ten_linh_vuc' => 'hóa'],
        ['ten_linh_vuc' => 'sinh'],
        ['ten_linh_vuc' => 'địa']
        ]);
    }
}
