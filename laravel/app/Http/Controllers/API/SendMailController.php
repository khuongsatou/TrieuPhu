<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Mail;
use App\Mail\DemoMail;
class SendMailController extends Controller
{
    public function send($id){
        
        $objDemo = new \stdClass();
        $objDemo->nguoi_nhan = "Mr Tuan";
        $objDemo->nguoi_gui = "Admin APP Trieu Phu";
        Mail::to("yoshi240499@gmail.com")->send(new DemoMail($objDemo));
    }
}
