import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from './model/client';
import { ConfirmationService, MessageService } from 'primeng/api';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [ConfirmationService, MessageService]

})
export class HomeComponent implements OnInit {
  projectCreationDialog: boolean = false;
  projectEditDialog:boolean=false;
  clientData!:Client;
  clientData1!:Client;
  clientDatas:Client[]=[];

  value1:number=100;
  value2:number=70;
  value3:number=30;

  clientForm!: FormGroup;

  constructor(private router: Router,private messageService: MessageService,private confirmationService: ConfirmationService) {}

  ngOnInit(): void {
    // this.service.getProjects().subscribe(
    //   (data:any)=>{
    //     this.clientDatas=data;
    //   },
    //   (error:HttpErrorResponse)=>{
    //     alert(error);
    //   }
    // )


    // this.clientForm=new FormGroup({
    //   clientName:new FormControl('',Validators.required),
    //   cafCode:new FormControl('',Validators.required),
    //   clientLocation:new FormControl('',Validators.required)
    // });
  }

  onClickAddProject() {
    // this.clientData={};
    // this.projectCreationDialog=true;
  }

  onClickCancle()
  {
    // this.projectCreationDialog=false;
    // this.projectEditDialog=false;
  }

  onClickSave()
  {
    // this.service.addProject(this.clientForm.value).subscribe(
    //   (data:Client)=>{
    //     console.log(data);
    //     this.projectCreationDialog=false;
    //     this.ngOnInit();
    //   },
    //   (error:HttpErrorResponse)=>{
    //     alert(error);
    //   }
    // )
  }

  onCLickEdit(id:string)
  {
    // this.clientData1={};
    // this.service.getProjectById(id).subscribe(
    //   (data:Client)=>{
    //     this.clientData1=data;
    //     this.projectEditDialog=true;
    //   },
    //   (error:HttpErrorResponse)=>{
    //     alert(error);
    //   }
    // )
  }

  onClickUpdate()
  {
  //   this.confirmationService.confirm({
  //     message: 'Are you sure that you want to perform this action?',
  //     accept: () => {
  //       this.service.editProjectById(this.clientData1).subscribe(
  //         (data:Client)=>{
  //           this.projectEditDialog=false;
  //           this.messageService.add({
  //             severity: 'success',
  //             summary: 'successs',
  //             detail: 'Client Updated Suucessfully',
  //           });
  //           this.ngOnInit();
  //         },
  //         (error:HttpErrorResponse)=>{
  //           this.messageService.add({
  //             severity: 'cancel',
  //             summary: 'error',
  //             detail: `${error}`,
  //           });
  //         }
  //       )
  //     },
  //     reject:()=>{
  //       this.messageService.add({
  //         severity: 'warn',
  //         summary: 'Cancelled',
  //         detail: 'Client not Edited',
  //       });
  //     }
  // });
   
  }
}
