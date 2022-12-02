import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './navbar/home/home.component';

import { SharedModulesModule } from 'src/app/shared-modules/shared-modules.module';
import { RouterModule } from '@angular/router';
import { NgxDocViewerModule } from 'ngx-doc-viewer';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { DocumentEditorContainerAllModule } from '@syncfusion/ej2-angular-documenteditor';
import { UserMngComponent } from './navbar/user-mng/user-mng.component';
import { RoleMngComponent } from './navbar/role-mng/role-mng.component';
import { ProjectMngComponent } from './navbar/project-mng/project-mng.component';
import { VendorMngComponent } from './navbar/vendor-mng/vendor-mng.component';
import { TemplateComponent } from './navbar/templates/template/template.component';
import { NewtemplateComponent } from './navbar/templates/template/newtemplate/newtemplate.component';
import { TemplateDetailsComponent } from './navbar/templates/template/newtemplate/template-details/template-details.component';
import { TemplateBuilderComponent } from './navbar/templates/template/newtemplate/template-builder/template-builder.component';
import { AddnewrowDirective } from './navbar/templates/template/newtemplate/template-builder/addnewrow.directive';
import { TableStructureComponent } from './navbar/templates/template/table-structure/table-structure.component';
import { TemplateDemoComponent } from './navbar/templates/template/newtemplate/template-demo/template-demo.component';
import { CombineCategoryDialogComponent } from './navbar/templates/template/newtemplate/template-demo/combine-category-dialog/combine-category-dialog.component';
import { MatDialogRef } from '@angular/material/dialog';

@NgModule({
  declarations: [NavbarComponent, HomeComponent, UserMngComponent, RoleMngComponent, VendorMngComponent, ProjectMngComponent, TemplateComponent, NewtemplateComponent, TemplateDetailsComponent, TemplateBuilderComponent, AddnewrowDirective, TableStructureComponent, TemplateDemoComponent, CombineCategoryDialogComponent],
  imports: [
    CommonModule,
    SharedModulesModule,
    NgxDocViewerModule,
    PdfViewerModule,
    DocumentEditorContainerAllModule,
    RouterModule.forChild([
      {
        path: '',
        component: NavbarComponent,
        children: [
          {
            path: 'home',
            component: HomeComponent,
          },
          {
            path:'user-mng',
            component:UserMngComponent
          },
          {
            path:'role-mng',
            component:RoleMngComponent
          },
          {
            path:'vendor-mng',
            component:VendorMngComponent
          },
          {
            path:'project-mng',
            component:ProjectMngComponent
          },
          {
            path:'template-mng',
            component:TemplateComponent
          },
          {
            path:'newtemplate',
            component:NewtemplateComponent,
            children: [
              {
                path:'templatedetails',
                component:TemplateDetailsComponent
              },
              {
                path:'templatebuilder',
                component:TemplateBuilderComponent
              },
              {
                path:'tableStructure',
                component:TableStructureComponent
              },
              {
                path:'tableDemo',
                component:TemplateDemoComponent
              }
            ]
          }
        ],
      },
    ]),
  ],
  exports: [NavbarComponent],
  providers: [
    {
      provide: MatDialogRef,
      useValue: {}
    }
  ]
})
export class HeadersModule {}
