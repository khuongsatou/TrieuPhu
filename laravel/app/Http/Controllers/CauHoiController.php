<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\CauHoi;
use App\LinhVuc;
class CauHoiController extends Controller
{
    private $dapAns = ['A','B','C','D'];
    
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $cauHois = CauHoi::all();
        return View('cau_hoi.ds_cau_hoi',compact('cauHois'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        $linhVucs = LinhVuc::all();
        $dapAns = $this->dapAns;
        return View('cau_hoi.xl_cau_hoi',compact('linhVucs','dapAns'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        
        $cauHoi = new CauHoi();
        $cauHoi->noi_dung =$request->noi_dung;
        $cauHoi->linh_vuc_id =$request->linh_vuc_id;
        $cauHoi->phuong_an_a =$request->phuong_an_a;
        $cauHoi->phuong_an_b =$request->phuong_an_b;
        $cauHoi->phuong_an_c =$request->phuong_an_c;
        $cauHoi->phuong_an_d =$request->phuong_an_d;
        $cauHoi->dap_an =$request->dap_an;
        $cauHoi->save();
        return redirect()->route('cau_hoi.danh_sach');
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
        $linhVucs = LinhVuc::all();
        $cauHoi = CauHoi::find($id);
        $dapAns = $this->dapAns;
        return View('cau_hoi.xl_cau_hoi',compact('cauHoi','linhVucs','dapAns'));
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
        $cauHoi =  CauHoi::find($id);
        $cauHoi->noi_dung =$request->noi_dung;
        $cauHoi->linh_vuc_id =$request->linh_vuc_id;
        $cauHoi->phuong_an_a =$request->phuong_an_a;
        $cauHoi->phuong_an_b =$request->phuong_an_b;
        $cauHoi->phuong_an_c =$request->phuong_an_c;
        $cauHoi->phuong_an_d =$request->phuong_an_d;
        $cauHoi->dap_an =$request->dap_an;
        $cauHoi->save();
        return redirect()->route('cau_hoi.danh_sach');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $cauHoi =  CauHoi::find($id);
        $cauHoi->delete();
        return redirect()->route('cau_hoi.danh_sach');
    }
}
