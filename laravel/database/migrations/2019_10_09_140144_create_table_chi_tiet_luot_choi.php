<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateTableChiTietLuotChoi extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('chi_tiet_luot_choi', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('luot_choi_id')->unsigned();
            $table->integer('cau_hoi_id')->unsigned();
            $table->string('phuong_an');
            $table->integer('diem');
            $table->timestamps();
            $table->softDeletes();
            
            $table->foreign('cau_hoi_id')->references('id')->on('cau_hoi');
            $table->foreign('luot_choi_id')->references('id')->on('luot_choi');
            
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('chi_tiet_luot_choi');
    }
}
