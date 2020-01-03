<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\GoiCredit;
class GoiCreditController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $goiCredits= GoiCredit::all();
        return View('goi_credit.ds_goi_credit',compact('goiCredits'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return View('goi_credit.xl_goi_credit');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $goiCredit= new GoiCredit();
        // $goiCredit->ten_goi = $request->ten_goi;
        if (strlen($request->ten_goi) > 250) {
            $loi_ten = "1";
            
            return View('goi_credit.xl_goi_credit',compact('loi_ten'));
        }
        else
        {
            $goiCredit->ten_goi = $request->ten_goi;
        }
        
        if (strlen($request->credit) > 6) {
            $loi_credit = "1";
            
           return View('goi_credit.xl_goi_credit',compact('loi_credit'));
        }
        else
        {
            $goiCredit->credit = $request->credit;
        }
       
        if (strlen($request->credit) > 5) {
            $loi_tien = "1";
            
           return View('goi_credit.xl_goi_credit',compact('loi_tien'));
        }
        else
        {
             $goiCredit->so_tien = $request->so_tien;
        }

        $goiCredit->save();
        return redirect()->route('goi_credit.danh_sach');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $goiCredit= GoiCredit::find($id);
        return View('goi_credit.xl_goi_credit',compact('goiCredit'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $goiCredit= GoiCredit::find($id);
        // $goiCredit->ten_goi = $request->ten_goi;
        // $goiCredit->credit = $request->credit;
        // $goiCredit->so_tien = $request->so_tien;
        if (strlen($request->ten_goi) > 250) {
            $loi_ten = "1";
            
            return View('goi_credit.xl_goi_credit',compact('loi_ten','goiCredit'));
        }
        else
        {
            $goiCredit->ten_goi = $request->ten_goi;
        }
        
        if (strlen($request->credit) > 6) {
            $loi_credit = "1";
            
           return View('goi_credit.xl_goi_credit',compact('loi_credit','goiCredit'));
        }
        else
        {
            $goiCredit->credit = $request->credit;
        }
       
        if (strlen($request->so_tien) > 6) {
            $loi_tien = "1";
            
           return View('goi_credit.xl_goi_credit',compact('loi_tien','goiCredit'));
        }
        else
        {
             $goiCredit->so_tien = $request->so_tien;
        }
        $goiCredit->save();
        return redirect()->route('goi_credit.danh_sach');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $goiCredit= GoiCredit::find($id);
        $goiCredit->delete();
        return redirect()->route('goi_credit.danh_sach');
    }
}
