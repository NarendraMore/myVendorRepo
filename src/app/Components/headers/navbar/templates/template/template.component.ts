import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { VendorMngServiceService } from 'src/app/vendor-mng-service.service';
import { Template } from './newtemplate/template-details/model/template';

@Component({
  selector: 'app-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.css']

})
export class TemplateComponent implements OnInit {

 
  templateDetails:Template[]=[];

  
  constructor(private router: Router,private service:VendorMngServiceService ) { }

  ngOnInit(): void {


    // to fetch all template details
    this.service.getTemplateDetails().subscribe((data:any)=>{

      this.templateDetails = data;
      console.log("hello",data);
      
      
    },
    (error:HttpErrorResponse)=>{
       
    })
    
  }

  onClickAddTemplate(){
    //this.addTemplateDialogBox = true;

    this.router.navigate(['/newtemplate/templatedetails']);
  }
  

  

}
