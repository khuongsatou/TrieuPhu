<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\CauHoi;

class CauHoiController extends Controller
{
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
        return View('cau_hoi.xl_cau_hoi');
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
        
        if (strlen($request->phuong_an_a) > 250) {
            $loi_a = "1";
            
            return View('cau_hoi.xl_cau_hoi',compact('loi_a'));
        }
        else
        {
            $cauHoi->phuong_an_a =$request->phuong_an_a;
        }
        
        if (strlen($request->phuong_an_b) > 250) {
            $loi_b = "1";
            
            return View('cau_hoi.xl_cau_hoi',compact('loi_b'));
        }
        else
        {
            $cauHoi->phuong_an_b =$request->phuong_an_b;
        }
        if (strlen($request->phuong_an_c) > 250) {
            $loi_c = "1";
            
            return View('cau_hoi.xl_cau_hoi',compact('loi_c'));
        }
        else
        {
            $cauHoi->phuong_an_c =$request->phuong_an_c;
        }
        if (strlen($request->phuong_an_d) > 250) {
            $loi_d = "1";
            
            return View('cau_hoi.xl_cau_hoi',compact('loi_d'));
        }
        else
        {
            $cauHoi->phuong_an_d =$request->phuong_an_d;
        }
        // $cauHoi->phuong_an_b =$request->phuong_an_b;
        // $cauHoi->phuong_an_c =$request->phuong_an_c;
        // $cauHoi->phuong_an_d =$request->phuong_an_d;

        
            
        $cauHoi->dap_an = $request->dap_an;
        
        
       
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
        $cauHoi = CauHoi::find($id);
        return View('cau_hoi.xl_cau_hoi',compact('cauHoi'));
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
        if (strlen($request->phuong_an_a) > 250) {
            $loi_a = "1";
            
            return View('cau_hoi.xl_cau_hoi',compact('loi_a','cauHoi'));
        }
        else
        {
            $cauHoi->phuong_an_a =$request->phuong_an_a;
        }
        
        if (strlen($request->phuong_an_b) > 250) {
            $loi_b = "1";
            
            return View('cau_hoi.xl_cau_hoi',compact('loi_b','cauHoi'));
        }
        else
        {
            $cauHoi->phuong_an_b =$request->phuong_an_b;
        }
        if (strlen($request->phuong_an_c) > 250) {
            $loi_c = "1";
            
            return View('cau_hoi.xl_cau_hoi',compact('loi_c','cauHoi'));
        }
        else
        {
            $cauHoi->phuong_an_c =$request->phuong_an_c;
        }
        if (strlen($request->phuong_an_d) > 250) {
            $loi_d = "1";
            
            return View('cau_hoi.xl_cau_hoi',compact('loi_d','cauHoi'));
        }
        else
        {
            $cauHoi->phuong_an_d =$request->phuong_an_d;
        }
        // $cauHoi->phuong_an_b =$request->phuong_an_b;
        // $cauHoi->phuong_an_c =$request->phuong_an_c;
        // $cauHoi->phuong_an_d =$request->phuong_an_d;

        if ($request->dap_an=='A'||$request->dap_an=='B'||$request->dap_an=='C'||$request->dap_an=='D') {
            
            $cauHoi->dap_an = $request->dap_an;
        }
        else
        {
            $loi_da="1";
           return View('cau_hoi.xl_cau_hoi',compact('loi_da','cauHoi'));
        }
       
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
