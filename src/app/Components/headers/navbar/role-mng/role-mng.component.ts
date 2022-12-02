import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ConfirmationService, MessageService } from "primeng/api";
import { VendorMngServiceService } from "src/app/vendor-mng-service.service";
import { Role } from "./model/role";

@Component({
  selector: "app-role-mng",
  templateUrl: "./role-mng.component.html",
  styleUrls: ["./role-mng.component.css"],
  providers: [ConfirmationService, MessageService],
})
export class RoleMngComponent implements OnInit {
  addRoleDialogBox: boolean = false;
  editRoleDialog: boolean = false;
  uploadDialog: boolean = false;
  checked: boolean = false;
  roleForm!: FormGroup;
  allRoles: any[] = [];
  roleData!: Role;

  roles: Role[] = [];
  

  selectedFiles?: FileList;
  selectedRole!: string;

  selectedAccess1: string[] = [];
  selectedAccess2: string[] = [];
  selectedAccess3: string[] = [];
  selectedId: string[] = [];

  access1: any[] = [
    { access: "View" },
    { access: "Edit" },
    { access: "Download" },
    { access: "Comment" },
  ];

  access2: any[] = [
    { access: "View" },
    { access: "Edit" },
    { access: "Download" },
    { access: "Comment" },
  ];

  access3: any[] = [
    { access: "View" },
    { access: "Edit" },
    { access: "Approve Request" },
  ];

  roleNamePattern = "^[a-zA-Z ]{3,30}$";
  // usersPattern =  "^[a-zA-Z ]{3,15}$";
  descriptionPattern = "^[a-zA-Z0-9 @#$%&]{3,255}$";
  // pwdPattern = "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{6,12}$";
  // mobnumPattern = "^((\\+91-?)|0)?[0-9]{10}$";
  // emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";

  constructor(
    private vendorService: VendorMngServiceService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    this.roleForm = new FormGroup({
      roleName: new FormControl('', [
        Validators.required,
        Validators.pattern(this.roleNamePattern),
      ]),
      descriptions: new FormControl("", [
        Validators.required,
        Validators.pattern(this.descriptionPattern),
      ]),
      // users: new FormControl("", [
      //   Validators.required,
      //   Validators.pattern(this.usersPattern),
      // ]),
    
      vendorTemplateAccess: new FormControl(""),
      dashboardAccess: new FormControl(""),
      masterRepoAccess: new FormControl(""),
      // createdOn: new FormControl(''),
      roleStatus: new FormControl(""),
      
    });

    // to fetch all roles
    this.vendorService.getuRole().subscribe(
      (data: any) => {
        this.allRoles = data;this.roleForm
        console.log(this.allRoles);
      },
      (error: HttpErrorResponse) => {
        alert("something went wrong");
      }
    );
  }

  onClickCheckBox() {
    console.log(this.selectedId);
  }

  onClickAddRole() {
    this.addRoleDialogBox = true;
  }

  handleChange(e: any) {
    this.checked = e.checked;
  }

  onClickSave() {
    // this.roleForm.value.vendorTemplateAccess = this.selectedAccess1;
    // this.roleForm.value.dashboardAccess = this.selectedAccess2;
    // this.roleForm.value.masterRepoAccess = this.selectedAccess3;
    // this.roleForm.value.createdOn = Date.now();
    this.roleForm.value.roleStatus = this.checked;

    console.log(this.roleForm.value, " roledata to be post");

    // this.vendorService.addRole(this.roleForm.value).subscribe((data: any) => {
    //   console.log(data);
    //   if (data.status === 201) {
    //     this.addRoleDialogBox = false;
    //     this.selectedAccess1 = [];
    //     this.selectedAccess2 = [];
    //     this.selectedAccess3 = [];
    //     this.ngOnInit();
    //     this.messageService.add({
    //       severity: 'success',
    //       summary: 'Successfull',
    //       detail: 'Role addedd successfully',
    //     });
    //   } else if(data.status===201) {
    //     (error: HttpErrorResponse) => {
    //       this.messageService.add({
    //         severity: 'Danger',
    //         summary: 'Error',
    //         detail: 'Something went wrong while adding user..!!',
    //       });
    //     };
    //   }
    // });

    this.vendorService.addRole(this.roleForm.value).subscribe(
      (data: any) => {
        this.messageService.add({
          severity: "success",
          summary: "Successfull",
          detail: "Role addedd successfully",
        });
        this.addRoleDialogBox = false;
        this.roleForm.reset();
        this.ngOnInit();
      },
      (error: HttpErrorResponse) => {
        this.messageService.add({
          severity: "error",
          summary: "Unsuccessfullerror",
          detail: "Error while adding user",
        });
        this.addRoleDialogBox = false;
        this.ngOnInit();
        this.roleForm.reset();
      }
    );
  }

  onClickUpdate() {
    this.roleForm.value.roleStatus = this.checked;

    this.confirmationService.confirm({
      message: "Are you sure that you want to edit this Role?",
      accept: () => {
        this.vendorService.updateRole(this.roleData).subscribe(
          (data: any) => {
            console.log("Role Updated" + data);
            this.messageService.add({
              severity: "success",
              summary: "Success",
              detail: "role updated Successfully",
            });
            this.editRoleDialog = false;
            this.roleForm.reset();
            this.ngOnInit();
          },
          (error: HttpErrorResponse) => {
            this.messageService.add({
              severity: "cancle",
              summary: "Cancelled",
              detail: `${error}`,
            });
            this.editRoleDialog = false;
            this.roleForm.reset();
            this.ngOnInit();
          }
        );
      },

      reject: () => {
        this.messageService.add({
          severity: "warn",
          summary: "Cancelled",
          detail: "role not updated",
        });
        this.roleForm.reset();
      },
    });
  }

  onClickCancle() {
    this.addRoleDialogBox = false;
    this.editRoleDialog = false;
    this.uploadDialog = false;
    this.roleForm.reset();
  }


  access12!: any[];
  editRole(id: string) {
    this.vendorService.getRoleById(id).subscribe(
      (data: any) => {
        this.roleData = data;
        // console.log((this.roleData.dashboardAccess[0].substring(1,(this.roleData.dashboardAccess[0].length-1)).split(','))," data ");
        console.log(this.roleData, " data to be edited");
        
        this.roleData.dashboardAccess = this.roleData.dashboardAccess[0]
          .substring(1, this.roleData.dashboardAccess[0].length - 1).split(",");
        this.roleData.masterRepoAccess = this.roleData.masterRepoAccess[0]
          .substring(1, this.roleData.masterRepoAccess[0].length - 1).split(",");
        this.roleData.vendorTemplateAccess = this.roleData.vendorTemplateAccess[0]
          .substring(1, this.roleData.vendorTemplateAccess[0].length - 1)
          .split(",");


        console.log(this.roleData.dashboardAccess, "data...");
       
        this.roleData.dashboardAccess = this.roleData.dashboardAccess.map(
          (element: any) => {
            return element.trim();
          }
        );
        this.roleData.masterRepoAccess = this.roleData.masterRepoAccess.map(
          (element: any) => {
            return element.trim();
          }
        );
        this.roleData.vendorTemplateAccess = this.roleData.vendorTemplateAccess.map(
          (element: any) => {
            return element.trim();
          }
        );

        // this.roleForm.get('roleName')?.patchValue(this.roleData.roleName);
        // this.roleForm.get('descriptions')?.patchValue(this.roleData.descriptions);
        // this.roleForm.get('dashboardAccess')?.patchValue(this.roleData.dashboardAccess);
        // this.roleForm.get('vendorTemplateAccess')?.patchValue(this.roleData.vendorTemplateAccess);
        // this.roleForm.get('masterRepoAccess')?.patchValue(this.roleData.masterRepoAccess);

        this.editRoleDialog = true;
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
  }

  deleteRole() {
    this.confirmationService.confirm({
      message: "Are you sure you want to delete the selected roles?",
      accept: () => {
        for (let id of this.selectedId) {
          this.vendorService.deleteRole(id).subscribe(
            (data: any) => {
              this.messageService.add({
                severity: "success",
                summary: "Deleted",
                detail: "Role deleted successfully",
              });
              this.ngOnInit();
            },
            (error: HttpErrorResponse) => {
              this.messageService.add({
                severity: "danger",
                summary: "Error",
                detail: "Something went wrong while deleting role..!!",
              });
              this.ngOnInit();
            }
          );
        }
        this.selectedId = [];
        this.ngOnInit();
      },
      reject: () => {
        this.messageService.add({
          severity: "warn",
          summary: "Cancelled",
          detail: "role not deleted",
        });
      },
    });
  }

  uploadRoles() {
    this.uploadDialog = true;
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  uploadFile() {
    alert("file uploaded successfully");
    this.uploadDialog = false;
  }
}
