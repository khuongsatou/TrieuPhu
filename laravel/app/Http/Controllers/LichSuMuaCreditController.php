<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\LichSuMuaCredit;
use App\NguoiChoi;
class LichSuMuaCreditController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $lichSuMuaCredits = LichSuMuaCredit::all();
        return View('lich_su_mua_credit.ds_lich_su_mua_credit',compact('lichSuMuaCredits'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return View('lich_su_mua_credit.xl_lich_su_mua_credit');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $lichSuMuaCredits = new LichSuMuaCredit();
        $lichSuMuaCredits ->ten_lich_su_mua_credit = $request->ten_lich_su_mua_credit;
        $lichSuMuaCredits ->save();
        return redirect()->route('lich_su_mua_credit.danh_sach');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $lichSuMuaCredits = LichSuMuaCredit::all();
        $nguoiChoi = NguoiChoi::find($id);
        return View('lich_su_mua_credit.ds_lich_su_mua_credit_id',compact('lichSuMuaCredits','nguoiChoi'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $lichSuMuaCredit = LichSuMuaCredit::find($id);
        return View('lich_su_mua_credit.xl_lich_su_mua_credit',compact('lichSuMuaCredit'));
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
        $lichSuMuaCredit = LichSuMuaCredit::find($id);
        $lichSuMuaCredit ->nguoi_choi_id = $request->ten_lich_su_mua_credit;
        $lichSuMuaCredit ->goi_credit_id = $request->goi_credit_id;
        $lichSuMuaCredit ->credit = $request->credit;
        $lichSuMuaCredit ->so_tien = $request->so_tien;

        $lichSuMuaCredit ->save();
        return redirect()->route('lich_su_mua_credit.danh_sach');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $lichSuMuaCredit = LichSuMuaCredit::find($id);
        $lichSuMuaCredit ->delete();
        return redirect()->route('lich_su_mua_credit.danh_sach');
    }
}
