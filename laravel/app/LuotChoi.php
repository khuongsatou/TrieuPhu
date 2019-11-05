<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class LuotChoi extends Model
{
    //
    protected $table = "luot_choi";

    function getNguoiChoi(){
        return $this->belongsTo('App\NguoiChoi');
    }
}
