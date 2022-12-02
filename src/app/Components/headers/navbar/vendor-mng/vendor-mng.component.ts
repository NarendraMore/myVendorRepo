import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';
import { VendorMngServiceService } from 'src/app/vendor-mng-service.service';
import { lineOfBusiness, Vendor } from './model/vendor';

@Component({
  selector: 'app-vendor-mng',
  templateUrl: './vendor-mng.component.html',
  styleUrls: ['./vendor-mng.component.css'],
  providers: [ConfirmationService, MessageService],
})
export class VendorMngComponent implements OnInit {
  addVendorDialogBox: boolean = false;
  editVendorDialog: boolean = false;
  checked: boolean = true;
  vendorForm!: FormGroup;
  allVendors: any[] = [];
  vendorData!: Vendor;

  allLineOfBusiness: lineOfBusiness[] = [];

  selectedFiles?: FileList;
  selectedLineOfBusiness!: string;
  selectedId: string[] = [];


  vendorNamePattern = "^[a-zA-Z ]{3,15}$";
  spocNamePattern = "^[a-zA-Z ]{3,15}$";
  mobnumPattern = "^((\\+91-?)|0)?[0-9]{10}$"; 
  emailPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";


  updateButton:boolean=false;
  save:boolean=false;
  update:boolean=false;


  constructor(
    private vendorService: VendorMngServiceService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
    this.allLineOfBusiness = [
      { businessName: 'Logistics' },
      { businessName: 'IT' },
      { businessName: 'Manufacturing' },
      { businessName: 'Retailing' },
      { businessName: 'Hospitality' },
      { businessName: 'Real Estate' },
    ];

    this.vendorForm = new FormGroup({
      vendorName: new FormControl('', [Validators.required,Validators.pattern(this.vendorNamePattern)]),
      spocName: new FormControl('', [Validators.required,Validators.pattern(this.spocNamePattern)]),
      email: new FormControl('', [Validators.required,Validators.pattern(this.emailPattern)]),
      contactNumber: new FormControl('', [Validators.required,Validators.pattern(this.mobnumPattern)]),
      lineOfBusiness: new FormControl(''),
      // createdOn: new FormControl(''),
    });

    // to fetch all users
    this.vendorService.getVendors().subscribe(
      (data: any) => {
        this.allVendors = data;
        console.log(this.allVendors);
      },
      (error: HttpErrorResponse) => {
        alert('something went wrong');
      }
    );
  }

  onClickAddVendor() {
    this.addVendorDialogBox = true;
    this.update=false;
    this.save=true;
  }

  handleChange(e: any) {
    this.checked = e.checked;
  }

  onClickSave() {
    // this.vendorForm.value.lineOfBusiness = this.selectedLineOfBusiness;

    // this.vendorService
    //   .addVendor(this.vendorForm.value)
    //   .subscribe((data: any) => {
    //    console.log(data);

    //     if (data.status === 200) {
    //       this.messageService.add({
    //         severity: 'success',
    //         summary: 'Successfull',
    //         detail: 'Vendor addedd successfully',
    //       });
    //       console.log(data,"response...!!");
    //       this.addVendorDialogBox = false;
    //       this.ngOnInit();
    //     } else if(data.status===404) {
    //       console.log(data,"response...!!");

    //     }
    //     else{
    //       alert("xyz")
    //     }
    //   });

    // this.vendorForm.value.lineOfBusiness = this.selectedLineOfBusiness;

    this.vendorService.addVendor(this.vendorForm.value).subscribe(
      (data: any) => {
        this.messageService.add({
          severity: 'success',
          summary: 'Successfull',
          detail: 'Vendor addedd successfully',
        });
        this.addVendorDialogBox=false;
        this.ngOnInit();

      },
      (error: any) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Unsuccessfull',
          detail: 'Error while Vendor adding ',
        });
        this.addVendorDialogBox=false;
        this.ngOnInit();
      }
    );
  }

  onClickUpdate() {
    console.log(this.vendorData);
    
    this.confirmationService.confirm({
      message: 'Are you sure that you want to edit this Vendor?',
      accept: () => {
        this.vendorService.updateVendor(this.vendorData).subscribe(
          (data: any) => {
            console.log('Vendor Updated' + data);
            this.messageService.add({
              severity: 'success',
              summary: 'Success',
              detail: 'Vendor updated Successfully',
            });
            this.editVendorDialog=false;
            
        this.ngOnInit();
          },
          (error: HttpErrorResponse) => {
            this.messageService.add({
              severity: 'cancle',
              summary: 'Cancelled',
              detail: `${error}`,
            });
            this.addVendorDialogBox=false;
            this.ngOnInit();
          }
        );
      },

      reject: () => {
        this.messageService.add({
          severity: 'warn',
          summary: 'Cancelled',
          detail: 'vendor not updated',
        });
      },
    });
  }

  onClickCancle() {
    this.addVendorDialogBox = false;
    this.editVendorDialog = false;
    this.updateButton=false;
  }

  

  editVendor(id: string) {
    // this.updateButton=true;
    this.vendorService.getVendorById(id).subscribe(
      (data: any) => {
        this.vendorData = data;
        // this.save=false;
        // this.update=true;
        // this.vendorForm.get('vendorName')?.patchValue(this.vendorData.vendorName);
        // this.vendorForm.get('spocName')?.patchValue(this.vendorData.spocName);
        // this.vendorForm.get('email')?.patchValue(this.vendorData.email);
        // this.vendorForm.get('contactNumber')?.patchValue(this.vendorData.contactNumber);
        // this.vendorForm.get('lineOfBusiness')?.patchValue(this.vendorData.lineOfBusiness);



        this.editVendorDialog = true;
        console.log('vendor data', this.vendorData);
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
  }

  deleteVendor(id: string) {
    //   console.log(id," id to be deleted");

    //   this.confirmationService.confirm({
    //     message: 'Are you sure that you want to Delete this Vendor?',
    //     accept: () => {
    //       this.vendorService.deleteVendor(id).subscribe(
    //         (data: any) => {
    //           this.messageService.add({
    //             severity: 'success',
    //             summary: 'Deleted',
    //             detail: 'Vendor deleted successfully',
    //           });
    //           this.editVendorDialog=false;
    //           this.ngOnInit();
    //         },
    //         (error: HttpErrorResponse) => {
    //           this.messageService.add({
    //             severity: 'danger',
    //             summary: 'Error',
    //             detail: 'Something went wrong while deleting vendor',
    //           });
    //         }
    //       );

    //       this.selectedId = [];
    //       this.ngOnInit();
    //     },
    //     reject: () => {
    //       this.messageService.add({
    //         severity: 'warn',
    //         summary: 'Cancelled',
    //         detail: 'Vendor not deleted',
    //       });
    //     },
    //   });

    this.confirmationService.confirm({
      message: 'Are you sure that you want to Delete this Vendor?',
      accept: () => {
        this.vendorService.deleteVendor(id).subscribe(
          (data: any) => {
            this.messageService.add({
              severity: 'success',
              summary: 'Successfully deleted',
              detail: 'Vendor deleted successfully',
            });
            this.editVendorDialog=false;
            this.ngOnInit();
          },
          (error: any) => {
            this.messageService.add({
              severity: 'success',
              summary: 'success',
              detail: 'Vendor deleted successfully',
            });
            this.editVendorDialog=false;
            this.ngOnInit();
          }
        );
      },
      reject: () => {
        this.messageService.add({
          severity: 'warn',
          summary: 'Cancelled',
          detail: 'Vendor not deleted',
        });
      },
    });
  }
}
