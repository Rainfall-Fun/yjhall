USE [yjh]
GO
/****** Object:  Table [dbo].[all_ddlx]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_ddlx](
  [ddlxbm] [int] IDENTITY(1000,1) NOT NULL,
  [ddlx] [varchar](60) NOT NULL,
  CONSTRAINT [pk_all_ddlx] PRIMARY KEY NONCLUSTERED
    (
      [ddlxbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_ddzt]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_ddzt](
  [ddztbm] [int] IDENTITY(1000,1) NOT NULL,
  [ddzt] [varchar](60) NOT NULL,
  CONSTRAINT [pk_all_ddzt] PRIMARY KEY NONCLUSTERED
    (
      [ddztbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_gys]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_gys](
  [gysbm] [varchar](40) NOT NULL,
  [ywqybm] [int] NULL,
  [gysmc] [varchar](200) NOT NULL,
  [gyshybh] [varchar](40) NULL,
  [lxr] [varchar](100) NULL,
  [lxdh] [varchar](100) NULL,
  [gysjj] [varchar](1000) NULL,
  [ssywy] [varchar](100) NULL,
  [tjr] [varchar](20) NOT NULL,
  [tjsj] [datetime] NOT NULL,
  [zhxgr] [varchar](20) NOT NULL,
  [zhxgsj] [datetime] NOT NULL,
  [bz] [varchar](255) NULL,
  CONSTRAINT [pk_all_gys] PRIMARY KEY NONCLUSTERED
    (
      [gysbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_hycllx]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_hycllx](
  [hycllxbm] [int] IDENTITY(1000,1) NOT NULL,
  [hycllx] [varchar](100) NOT NULL,
  CONSTRAINT [pk_all_hycllx] PRIMARY KEY NONCLUSTERED
    (
      [hycllxbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_hydj]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_hydj](
  [hydjbm] [int] NOT NULL,
  [hydj] [varchar](100) NOT NULL,
  CONSTRAINT [pk_all_hydj] PRIMARY KEY NONCLUSTERED
    (
      [hydjbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_kzsx]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_kzsx](
  [kzsxbm] [int] IDENTITY(1000,1) NOT NULL,
  [sxmc] [varchar](100) NOT NULL,
  [sxsjlx] [int] NULL,
  [kd] [int] NULL,
  [xsws] [int] NULL,
  CONSTRAINT [pk_all_kzsx] PRIMARY KEY NONCLUSTERED
    (
      [kzsxbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_shdz]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_shdz](
  [dzbm] [int] IDENTITY(1000,1) NOT NULL,
  [shdr] [varchar](20) NOT NULL,
  [shdz] [varchar](200) NOT NULL,
  [sheng] [varchar](20) NOT NULL,
  [shi] [varchar](20) NOT NULL,
  [xian] [varchar](20) NOT NULL,
  [qybm] [varchar](10) NOT NULL,
  [dhhm] [varchar](15) NOT NULL,
  [sfsmrdz] [int] NULL,
  [yhbm] [int] NOT NULL,
  CONSTRAINT [PK_all_shdz] PRIMARY KEY CLUSTERED
    (
      [dzbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_shzt]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_shzt](
  [shztbm] [int] NOT NULL,
  [shzt] [varchar](40) NOT NULL,
  CONSTRAINT [pk_all_shzt] PRIMARY KEY NONCLUSTERED
    (
      [shztbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_spfl]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_spfl](
  [spflbm] [int] IDENTITY(1000,1) NOT NULL,
  [ssbkbm] [int] NOT NULL,
  [spfl] [varchar](100) NOT NULL,
  [sjspflbm] [int] NOT NULL,
  [xspx] [int] NULL,
  [sfsx] [int] NOT NULL,
  [spfltpwj] [varchar](255) NULL,
  [tjsj] [datetime] NOT NULL,
  [tjr] [varchar](20) NOT NULL,
  [zhxgr] [varchar](20) NOT NULL,
  [zhxgsj] [datetime] NOT NULL,
  [bz] [varchar](255) NULL,
  [fldj] [int] NULL,
  CONSTRAINT [pk_all_spfl] PRIMARY KEY NONCLUSTERED
    (
      [spflbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_spgg]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_spgg](
  [spggbm] [int] IDENTITY(1000,1) NOT NULL,
  [spbm] [int] NOT NULL,
  [ggmc] [varchar](200) NOT NULL,
  [ggz] [varchar](200) NOT NULL,
  CONSTRAINT [pk_all_spgg] PRIMARY KEY NONCLUSTERED
    (
      [spggbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_spjbxx]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_spjbxx](
  [spbm] [int] IDENTITY(1,1) NOT NULL,
  [spflbm] [varchar](500) NULL,
  [zffsbm] [int] NOT NULL,
  [yfgzbm] [int] NULL,
  [spppbm] [int] NULL,
  [gysbm] [varchar](40) NOT NULL,
  [ssbkbm] [int] NULL,
  [spmc] [varchar](200) NOT NULL,
  [sphh] [varchar](100) NULL,
  [jldw] [varchar](40) NULL,
  [spsj] [numeric](16, 2) NULL,
  [scj] [numeric](16, 2) NULL,
  [cbj] [numeric](16, 2) NULL,
  [sf] [numeric](16, 2) NULL,
  [kcsl] [numeric](16, 2) NULL,
  [xspx] [int] NULL,
  [cpms] [varchar](2000) NULL,
  [sfsj] [int] NOT NULL,
  [sfstjsp] [int] NOT NULL,
  [tjr] [varchar](20) NOT NULL,
  [tjsj] [datetime] NOT NULL,
  [zhxgr] [varchar](20) NOT NULL,
  [zhxgsj] [datetime] NOT NULL,
  [szsxjr] [varchar](20) NOT NULL,
  [zhszsxjsj] [datetime] NOT NULL,
  [zhsztjr] [varchar](20) NOT NULL,
  [zhsztjsj] [datetime] NOT NULL,
  CONSTRAINT [pk_all_spjbxx] PRIMARY KEY NONCLUSTERED
    (
      [spbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_spjkctp]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_spjkctp](
  [tpxh] [bigint] IDENTITY(1000,1) NOT NULL,
  [spbm] [int] NOT NULL,
  [spkcbm] [int] NULL,
  [tpljjmc] [varchar](255) NOT NULL,
  CONSTRAINT [pk_all_spjkctp] PRIMARY KEY NONCLUSTERED
    (
      [tpxh] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_spkc]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_spkc](
  [spkcbm] [int] IDENTITY(1000,1) NOT NULL,
  [spbm] [int] NOT NULL,
  [xlggz] [varchar](30) NOT NULL,
  [spsj] [numeric](16, 2) NOT NULL,
  [cbj] [numeric](16, 2) NULL,
  [jfzddked] [numeric](16, 2) NULL,
  [kc] [int] NOT NULL,
  [tjr] [varchar](20) NULL,
  [tjsj] [datetime] NULL,
  [zhxgr] [varchar](20) NULL,
  [zhxgsj] [datetime] NULL,
  CONSTRAINT [PK_all_spkc] PRIMARY KEY CLUSTERED
    (
      [spkcbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_spkzsxz]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_spkzsxz](
  [kzsxbm] [int] NOT NULL,
  [spbm] [int] NOT NULL,
  [kzsxz] [varchar](255) NULL,
  CONSTRAINT [pk_all_spkzsxz] PRIMARY KEY CLUSTERED
    (
      [kzsxbm] ASC,
      [spbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_splbkzsx]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_splbkzsx](
  [spflbm] [int] NOT NULL,
  [kzsxbm] [int] NOT NULL,
  CONSTRAINT [pk_all_splbkzsx] PRIMARY KEY CLUSTERED
    (
      [spflbm] ASC,
      [kzsxbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_sppp]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_sppp](
  [spppbm] [int] IDENTITY(1000,1) NOT NULL,
  [ppmc] [varchar](140) NOT NULL,
  [ppwz] [varchar](255) NULL,
  [xspx] [int] NULL,
  [sfxs] [int] NOT NULL,
  [ppjj] [varchar](1000) NULL,
  [tjsj] [datetime] NOT NULL,
  [tjr] [varchar](20) NOT NULL,
  [zhxgr] [varchar](20) NOT NULL,
  [zhxgsj] [datetime] NOT NULL,
  [bz] [varchar](255) NULL,
  CONSTRAINT [pk_all_sppp] PRIMARY KEY NONCLUSTERED
    (
      [spppbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_spssbk]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_spssbk](
  [ssbkbm] [int] IDENTITY(1000,1) NOT NULL,
  [ssbk] [varchar](100) NOT NULL,
  [bz] [varchar](255) NULL,
  CONSTRAINT [pk_all_spssbk] PRIMARY KEY NONCLUSTERED
    (
      [ssbkbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_yfgz]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_yfgz](
  [yfgzbm] [int] IDENTITY(1000,1) NOT NULL,
  [yfgz] [varchar](100) NOT NULL,
  [sm] [varchar](255) NULL,
  CONSTRAINT [pk_all_yfgz] PRIMARY KEY NONCLUSTERED
    (
      [yfgzbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_yfgzgc]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_yfgzgc](
  [fygzbm] [int] IDENTITY(1000,1) NOT NULL,
  [yfgzbm] [int] NOT NULL,
  [fygz] [varchar](60) NOT NULL,
  [fy] [numeric](16, 2) NOT NULL,
  [sfyx] [int] NOT NULL,
  [szsj] [datetime] NOT NULL,
  [bz] [varchar](255) NULL,
  CONSTRAINT [pk_all_yfgzgc] PRIMARY KEY NONCLUSTERED
    (
      [fygzbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_ywqy]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_ywqy](
  [ywqybm] [int] NOT NULL,
  [qymc] [varchar](100) NOT NULL,
  [sjqybm] [int] NOT NULL,
  [qydj] [int] NOT NULL,
  [bz] [varchar](255) NULL,
  CONSTRAINT [pk_all_ywqy] PRIMARY KEY NONCLUSTERED
    (
      [ywqybm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[all_zffs]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[all_zffs](
  [zffsbm] [int] IDENTITY(1000,1) NOT NULL,
  [zffs] [varchar](100) NOT NULL,
  [sm] [varchar](255) NULL,
  CONSTRAINT [pk_all_zffs] PRIMARY KEY NONCLUSTERED
    (
      [zffsbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hy_cl]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hy_cl](
  [hyrzclbm] [bigint] NOT NULL,
  [hybh] [varchar](40) NOT NULL,
  [hycllxbm] [int] NOT NULL,
  [clmc] [varchar](100) NOT NULL,
  CONSTRAINT [pk_hy_cl] PRIMARY KEY NONCLUSTERED
    (
      [hyrzclbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hy_clwj]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hy_clwj](
  [clwjbm] [bigint] NOT NULL,
  [hyrzclbm] [bigint] NOT NULL,
  [mc] [varchar](200) NULL,
  [wjm] [varchar](255) NULL,
  CONSTRAINT [pk_hy_clwj] PRIMARY KEY NONCLUSTERED
    (
      [clwjbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hy_hyjbxx]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hy_hyjbxx](
  [hybh] [int] IDENTITY(1000,1) NOT NULL,
  [hyztbm] [int] NULL,
  [shztbm] [int] NOT NULL,
  [hydjbm] [int] NULL,
  [xm] [varchar](60) NULL,
  [yhm] [varchar](20) NOT NULL,
  [sfzh] [varchar](20) NULL,
  [lxsj1] [varchar](11) NOT NULL,
  [lxsj2] [varchar](11) NULL,
  [yqr] [varchar](40) NOT NULL,
  [sjrz] [int] NOT NULL,
  [sjrzsj] [datetime] NULL,
  [smrz] [int] NOT NULL,
  [smrzsj] [datetime] NULL,
  [dlmm] [varchar](70) NOT NULL,
  [zfmm] [varchar](40) NULL,
  [qqsj] [datetime] NOT NULL,
  [shsj] [datetime] NULL,
  [shdr] [varchar](20) NULL,
  [zhbgyqrclr] [varchar](20) NULL,
  [zhbgyqrsj] [datetime] NULL,
  [dqcc] [int] NULL,
  CONSTRAINT [pk_hy_hyjbxx] PRIMARY KEY NONCLUSTERED
    (
      [hybh] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hy_hyzt]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hy_hyzt](
  [hyztbm] [int] NOT NULL,
  [hyzt] [varchar](40) NOT NULL,
  CONSTRAINT [pk_hy_hyzt] PRIMARY KEY NONCLUSTERED
    (
      [hyztbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hy_hyztxgjl]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hy_hyztxgjl](
  [hyztxgjlbm] [int] IDENTITY(1000,1) NOT NULL,
  [hyztbm] [int] NOT NULL,
  [hybh] [varchar](40) NOT NULL,
  [xgr] [varchar](20) NOT NULL,
  [xgsj] [datetime] NOT NULL,
  [sm] [varchar](1000) NULL,
  CONSTRAINT [pk_hy_hyztxgjl] PRIMARY KEY NONCLUSTERED
    (
      [hyztxgjlbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[hy_shjl]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hy_shjl](
  [hyshjlbm] [bigint] NOT NULL,
  [shztbm] [int] NOT NULL,
  [hybh] [varchar](40) NOT NULL,
  [shr] [varchar](20) NOT NULL,
  [shsj] [datetime] NOT NULL,
  [yysm] [varchar](1000) NULL,
  CONSTRAINT [pk_hy_shjl] PRIMARY KEY NONCLUSTERED
    (
      [hyshjlbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_js]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_js](
  [jsbm] [int] IDENTITY(1000,1) NOT NULL,
  [jsmc] [varchar](60) NOT NULL,
  CONSTRAINT [pk_sys_js] PRIMARY KEY NONCLUSTERED
    (
      [jsbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_jsqx]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_jsqx](
  [jsbm] [int] NOT NULL,
  [qxbm] [int] NOT NULL,
  CONSTRAINT [pk_sys_jsqx] PRIMARY KEY CLUSTERED
    (
      [jsbm] ASC,
      [qxbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_qx]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_qx](
  [qxbm] [int] IDENTITY(1000,1) NOT NULL,
  [qxfzbm] [int] NOT NULL,
  [qx] [varchar](100) NOT NULL,
  [qxdz] [varchar](500) NOT NULL,
  [sm] [varchar](255) NULL,
  CONSTRAINT [pk_sys_qx] PRIMARY KEY NONCLUSTERED
    (
      [qxbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_qxz]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_qxz](
  [qxfzbm] [int] IDENTITY(1000,1) NOT NULL,
  [qxfzm] [varchar](100) NOT NULL,
  [sm] [varchar](255) NULL,
  CONSTRAINT [pk_sys_qxz] PRIMARY KEY NONCLUSTERED
    (
      [qxfzbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_yh]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_yh](
  [yhbm] [varchar](20) NOT NULL,
  [gysbm] [varchar](40) NULL,
  [yhlbbm] [int] NOT NULL,
  [jsbm] [int] NULL,
  [yhm] [varchar](40) NOT NULL,
  [mm] [varchar](65) NOT NULL,
  [sfsbdw] [int] NOT NULL,
  [sfyx] [int] NOT NULL,
  CONSTRAINT [pk_sys_yh] PRIMARY KEY NONCLUSTERED
    (
      [yhbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_yhlb]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_yhlb](
  [yhlbbm] [int] IDENTITY(1000,1) NOT NULL,
  [yhlb] [varchar](100) NOT NULL,
  CONSTRAINT [pk_sys_yhlb] PRIMARY KEY NONCLUSTERED
    (
      [yhlbbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sys_yhqx]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sys_yhqx](
  [yhbm] [varchar](20) NOT NULL,
  [qxbm] [int] NOT NULL,
  CONSTRAINT [pk_sys_yhqx] PRIMARY KEY CLUSTERED
    (
      [yhbm] ASC,
      [qxbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[xs_dd]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[xs_dd](
  [ddbm] [bigint] NOT NULL,
  [zffsbm] [int] NULL,
  [ddlxbm] [int] NULL,
  [ddztbm] [int] NULL,
  [shdzbm] [int] NULL,
  [ddh] [varchar](40) NOT NULL,
  [xdsj] [datetime] NOT NULL,
  [ddze] [numeric](16, 4) NOT NULL,
  [mjhyh] [varchar](40) NOT NULL,
  [shr] [varchar](60) NULL,
  [lxdh] [varchar](60) NULL,
  [zfsj] [datetime] NULL,
  [wcsj] [datetime] NULL,
  CONSTRAINT [pk_xs_dd] PRIMARY KEY NONCLUSTERED
    (
      [ddbm] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[xs_ddmx]    Script Date: 2019/8/10 13:46:59 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[xs_ddmx](
  [ddmxh] [bigint] NOT NULL,
  [ddbm] [bigint] NOT NULL,
  [spbm] [int] NOT NULL,
  [spggsxzbm] [int] NULL,
  [spdj] [numeric](16, 2) NOT NULL,
  [spsl] [numeric](16, 4) NOT NULL,
  [je] [numeric](16, 2) NOT NULL,
  [bz] [varchar](255) NULL,
  CONSTRAINT [pk_xs_ddmx] PRIMARY KEY NONCLUSTERED
    (
      [ddmxh] ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[all_spfl] ADD  DEFAULT ((0)) FOR [sjspflbm]
GO
ALTER TABLE [dbo].[all_spfl] ADD  DEFAULT ((1)) FOR [sfsx]
GO
ALTER TABLE [dbo].[all_spjbxx] ADD  DEFAULT ((0)) FOR [sf]
GO
ALTER TABLE [dbo].[all_spjbxx] ADD  DEFAULT ((0)) FOR [sfsj]
GO
ALTER TABLE [dbo].[all_spjbxx] ADD  DEFAULT ((0)) FOR [sfstjsp]
GO
ALTER TABLE [dbo].[all_sppp] ADD  DEFAULT ((1)) FOR [sfxs]
GO
ALTER TABLE [dbo].[all_yfgzgc] ADD  DEFAULT ((0)) FOR [fy]
GO
ALTER TABLE [dbo].[all_yfgzgc] ADD  DEFAULT ((1)) FOR [sfyx]
GO
ALTER TABLE [dbo].[hy_hyjbxx] ADD  DEFAULT ((2)) FOR [sjrz]
GO
ALTER TABLE [dbo].[hy_hyjbxx] ADD  DEFAULT ((2)) FOR [smrz]
GO
ALTER TABLE [dbo].[sys_yh] ADD  DEFAULT ((1)) FOR [sfsbdw]
GO
ALTER TABLE [dbo].[sys_yh] ADD  DEFAULT ((1)) FOR [sfyx]
GO
ALTER TABLE [dbo].[xs_ddmx] ADD  DEFAULT ((0)) FOR [spsl]
GO
ALTER TABLE [dbo].[xs_ddmx] ADD  DEFAULT ((0)) FOR [je]
GO
ALTER TABLE [dbo].[all_kzsx]  WITH CHECK ADD  CONSTRAINT [ckc_sxsjlx_all_kzsx] CHECK  (([sxsjlx] IS NULL OR ([sxsjlx]=(3) OR [sxsjlx]=(2) OR [sxsjlx]=(1))))
GO
ALTER TABLE [dbo].[all_kzsx] CHECK CONSTRAINT [ckc_sxsjlx_all_kzsx]
GO
ALTER TABLE [dbo].[all_spfl]  WITH CHECK ADD  CONSTRAINT [ckc_sfsx_all_spfl] CHECK  (([sfsx]=(0) OR [sfsx]=(1)))
GO
ALTER TABLE [dbo].[all_spfl] CHECK CONSTRAINT [ckc_sfsx_all_spfl]
GO
ALTER TABLE [dbo].[all_spjbxx]  WITH CHECK ADD  CONSTRAINT [ckc_sfsj_all_spjb] CHECK  (([sfsj]=(2) OR [sfsj]=(1) OR [sfsj]=(0)))
GO
ALTER TABLE [dbo].[all_spjbxx] CHECK CONSTRAINT [ckc_sfsj_all_spjb]
GO
ALTER TABLE [dbo].[all_spjbxx]  WITH CHECK ADD  CONSTRAINT [ckc_sfstjsp_all_spjb] CHECK  (([sfstjsp]=(1) OR [sfstjsp]=(0)))
GO
ALTER TABLE [dbo].[all_spjbxx] CHECK CONSTRAINT [ckc_sfstjsp_all_spjb]
GO
ALTER TABLE [dbo].[all_sppp]  WITH CHECK ADD  CONSTRAINT [ckc_sfxs_all_sppp] CHECK  (([sfxs]=(0) OR [sfxs]=(1)))
GO
ALTER TABLE [dbo].[all_sppp] CHECK CONSTRAINT [ckc_sfxs_all_sppp]
GO
ALTER TABLE [dbo].[all_yfgzgc]  WITH CHECK ADD  CONSTRAINT [ckc_sfyx_all_yfgz] CHECK  (([sfyx]=(0) OR [sfyx]=(1)))
GO
ALTER TABLE [dbo].[all_yfgzgc] CHECK CONSTRAINT [ckc_sfyx_all_yfgz]
GO
ALTER TABLE [dbo].[hy_hyjbxx]  WITH CHECK ADD  CONSTRAINT [ckc_sjrz_hy_hyjbx] CHECK  (([sjrz]=(2) OR [sjrz]=(1)))
GO
ALTER TABLE [dbo].[hy_hyjbxx] CHECK CONSTRAINT [ckc_sjrz_hy_hyjbx]
GO
ALTER TABLE [dbo].[hy_hyjbxx]  WITH CHECK ADD  CONSTRAINT [ckc_smrz_hy_hyjbx] CHECK  (([smrz]=(2) OR [smrz]=(1)))
GO
ALTER TABLE [dbo].[hy_hyjbxx] CHECK CONSTRAINT [ckc_smrz_hy_hyjbx]
GO
ALTER TABLE [dbo].[sys_yh]  WITH CHECK ADD  CONSTRAINT [ckc_sfsbdw_sys_yh] CHECK  (([sfsbdw]=(0) OR [sfsbdw]=(1)))
GO
ALTER TABLE [dbo].[sys_yh] CHECK CONSTRAINT [ckc_sfsbdw_sys_yh]
GO
ALTER TABLE [dbo].[sys_yh]  WITH CHECK ADD  CONSTRAINT [ckc_sfyx_sys_yh] CHECK  (([sfyx]=(0) OR [sfyx]=(1)))
GO
ALTER TABLE [dbo].[sys_yh] CHECK CONSTRAINT [ckc_sfyx_sys_yh]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单状态一般为： 1 待付款  2 待发货 3 待确认收货  4 卖家关闭  5 卖家关闭  6 交易成功 7 已过期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'all_ddzt'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核状态一般分为：1 待审核 2 审核通过 3 审核未通过 4 已退回' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'all_shzt'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商品所属板块一般包含：1 自营商城 2 特殊商品，其中特殊商品会员购买不会产生分润提佣。' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'all_spssbk'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'运费规则一般包含：1 满额包邮  2 基础运费  3 包邮' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'all_yfgz'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'支付方式一般分为：1 现金+积分  2 仅现金  3 仅积分' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'all_zffs'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户类别：1 本公司  2 供货商 ....' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'sys_yhlb'
GO
