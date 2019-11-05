<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class NguoiChoi extends Model
{
    //
    protected $table = "nguoi_choi";


    function getLuotChoi(){
        return $this->hasMany('App\LuotChoi');
    }
}
