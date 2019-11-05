<?php

use Illuminate\Database\Seeder;

class ThemGoiCreditSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('goi_credit')->insert([
            ['ten_goi'=>'gói 50k',
            'credit' =>200,
            'so_tien'=>50
            ]
        ]);
    }
}
