<?php

namespace App;

use Illuminate\Foundation\Auth\User as Authenticatable;
use Tymon\JWTAuth\Contracts\JWTSubject;
class NguoiChoi extends Authenticatable implements JWTSubject
{
    //
    protected $table = "nguoi_choi";


    function getLuotChoi(){
        return $this->hasMany('App\LuotChoi');
    }

    protected $hidden = ['mat_khau'];

    public function getPasswordAttribute(){
        return $this->mat_khau;
    }
    public function getJWTIdentifier()
    {
        return $this->getKey();
    }
    public function getJWTCustomClaims()
    {
        return [];
    }

}
