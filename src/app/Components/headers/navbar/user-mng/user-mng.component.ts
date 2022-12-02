import { OnInit,Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { VendorMngServiceService } from 'src/app/vendor-mng-service.service';
import { Manager, Role, User } from './model/user';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-user-mng',
  templateUrl: './user-mng.component.html',
  styleUrls: ['./user-mng.component.css'],
  providers: [ConfirmationService, MessageService],
})
export class UserMngComponent implements OnInit {
  addUserDialogBox: boolean = false;
  editUserDialog: boolean = false;
  uploadDialog: boolean = false;
  checked: boolean = true;

  userForm!: FormGroup;
  allUsers: any[] = [];
  userData!: User;

  roles: Role[] = [];
  roles1: Role[] = [];
  managers: Manager[] = [];
  partners: Manager[] = [];

  selectedFiles?: FileList;
  selectedRole!: string;
  selectedManager!: string;
  selectedPartner!: string;
  selectedAccess1: string[] = [];
  selectedAccess2: string[] = [];
  selectedAccess3: string[] = [];
  selectedId: string[] = [];

  access1: any[] = [
    { access: 'View' },
    { access: 'Edit' },
    { access: 'Download' },
    { access: 'Comment' },
  ];

  access2: any[] = [
    { access: 'View' },
    { access: 'Edit' },
    { access: 'Download' },
    { access: 'Comment' },
  ];

  access3: any[] = [
    { access: 'View' },
    { access: 'Edit' },
    { access: 'Approve Request' },
  ];

  firstNamePattern = "^[a-zA-Z ]{3,30}$";
  lastNamePattern = "^[a-zA-Z ]{3,30}$";
  // pwdPattern = "^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).{6,12}$";
  mobnumPattern = "^((\\+91-?)|0)?[7,8,9]{1}[0-9]{9}$"; 
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";





  constructor(
    private vendorService: VendorMngServiceService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {
    this.roles = [
      { role: 'Super Admin' },
      { role: 'Admin' },
      { role: 'client' },
      { role: 'Business User' },
    ];

    this.managers = [
      { name: 'Akshay Shinde' },
      { name: 'Siddesh Kadus' },
      { name: 'Shivraj Godle' },
      { name: 'Tasdiq Shaikh' },
    ];

    this.partners = [
      { name: 'Shubham Saykar' },
      { name: 'Upendra Kale' },
      { name: 'Krushna Kure' },
      { name: 'Abhishek Gawade' },
    ];
  }

  ngOnInit(): void {

    
    
    this.userForm = new FormGroup({
      firstName: new FormControl('', [Validators.required, Validators.pattern(this.firstNamePattern)]),
      lastName: new FormControl('', [Validators.required, Validators.pattern(this.lastNamePattern)]),
      mobileNumber: new FormControl('', [Validators.required,Validators.pattern(this.mobnumPattern)]),
      email: new FormControl('', [Validators.required,Validators.pattern(this.emailPattern)]),
      // accessItem: new FormControl('', Validators.required),
      roleName: new FormControl(''),
      manager: new FormControl(''),
      partner: new FormControl(''),
      // vendorTemplateAccess: new FormControl(''),
      // dashboardAccess: new FormControl(''),
      // masterRepoAccess: new FormControl(''),
      // createdOn: new FormControl(''),
      userStatus: new FormControl(''),
    });

    // to fetch all roles

    this.vendorService.getRoles().subscribe(
      (data: any) => {
        console.log(data, ' AKshayayayayay');

        this.roles1 = data;
        console.log(this.roles1);
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );

    // to fetch all users
    this.vendorService.getuUser().subscribe(
      (data: any) => {
        this.allUsers = data;
        console.log(this.allUsers);
        
      },
      (error: HttpErrorResponse) => {
        alert('something went wrong');
      }
    );
  }

  onClickAddUser() {
    this.addUserDialogBox = true;
  }

  handleChange(e: any) {
    this.checked = e.checked;
  }

  onClickSave() {
    // this.userForm.value.roleId = this.selectedRole;
    // this.userForm.value.manager = this.selectedManager;
    // this.userForm.value.partner = this.selectedPartner;
   
    this.userForm.value.userStatus = this.checked;
    console.log(this.userForm.value," all data to be post");

    this.vendorService.addUser(this.userForm.value).subscribe(
      (data: any) => {
        console.log(data);
        this.addUserDialogBox = false;
        
        this.ngOnInit();
        this.messageService.add({
          severity: 'success',
          summary: 'Successfull',
          detail: 'user addedd successfully',
        });
        this.addUserDialogBox=false;
        this.ngOnInit();
      },
      (error: HttpErrorResponse) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Unsuccessfull',
          detail: 'Error while adding user',
        });
        this.addUserDialogBox=false;
        this.ngOnInit();
      }
    );
  }

  onClickUpdate() {
    this.userData.status = this.checked;

    this.confirmationService.confirm({
      message: 'Are you sure that you want to edit this user?',
      accept: () => {
        this.vendorService.updateUser(this.userData).subscribe(
          (data: any) => {
            console.log('User Updated' + data);
            this.messageService.add({
              severity: 'success',
              summary: 'Success',
              detail: 'user updated Successfully',
            });
            this.editUserDialog = false;
            this.ngOnInit();
          },
          (error: HttpErrorResponse) => {
            this.messageService.add({
              severity: 'cancle',
              summary: 'Cancelled',
              detail: `${error}`,
            });
            this.editUserDialog = false;
            this.ngOnInit();
          }
        );
      },

      reject: () => {
        this.messageService.add({
          severity: 'warn',
          summary: 'Cancelled',
          detail: 'user not updated',
        });
      },
    });
  }

  onClickCancle() {
    this.addUserDialogBox = false;
    this.editUserDialog = false;
    this.uploadDialog = false;
  }

  editUser(id: string) {
    this.vendorService.getUserById(id).subscribe(
      (data: any) => {
        this.userData = data;
        this.editUserDialog = true;
      },
      (error: HttpErrorResponse) => {
        this.messageService.add({
          severity: 'Danger',
          summary: 'Error',
          detail: 'Something went wrong while adding user..!!',
        });
      }
    );
  }

  deleteUser() {
    this.confirmationService.confirm({
      message: 'Are you sure that you want to Delete User?',
      accept: () => {
        for (let id of this.selectedId) {
          this.vendorService.deleteUser(id).subscribe(
            (data: any) => {
              this.messageService.add({
                severity: 'success',
                summary: 'Deleted',
                detail: 'user deleted successfully',
              });
              this.ngOnInit();
            },
            (error: HttpErrorResponse) => {
              this.messageService.add({
                severity: 'Danger',
                summary: 'Error',
                detail: 'Something went wrong while deleting user..!!',
              });
            }
          );
        }
        this.selectedId=[];
        this.ngOnInit();
      },
      reject: () => {
        this.messageService.add({
          severity: 'warn',
          summary: 'Cancelled',
          detail: 'user not deleted',
        });
      },
    });
  }

  uploadUsers() {
    this.uploadDialog = true;
  }

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  uploadFile() {
    alert('file uploaded successfully');
    this.uploadDialog = false;
  }

  roleSelection(){
    // alert(this.selectedRole)
  }
}
