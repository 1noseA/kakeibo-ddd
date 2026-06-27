drop table if exists category_budgets;
drop table if exists monthly_budgets;
drop table if exists expenses;
drop table if exists incomes;
drop table if exists categories;

-- カテゴリー
create table categories (
    id uuid primary key,
    name varchar(50) not null,
    type varchar(20) not null,
    active boolean not null,
    created_at timestamp not null,
    updated_at timestamp not null,

    constraint uk_categories_name_type unique (name, type),
    constraint ck_categories_type check (type in ('INCOME', 'EXPENSE'))
);

-- 収入
create table incomes (
    id uuid primary key,
    entry_date date not null,
    amount int not null,
    category_id uuid not null,
    memo varchar(255),
    created_at timestamp not null,
    updated_at timestamp not null,

    constraint fk_incomes_category
        foreign key (category_id)
        references categories(id),

    constraint ck_incomes_amount
        check (amount > 0)
);

-- 支出
create table expenses (
    id uuid primary key,
    entry_date date not null,
    amount int not null,
    category_id uuid not null,
    memo varchar(255),
    created_at timestamp not null,
    updated_at timestamp not null,

    constraint fk_expenses_category
        foreign key (category_id)
        references categories(id),

    constraint ck_expenses_amount
        check (amount > 0)
);

-- 月次予算
create table monthly_budgets (
    id uuid primary key,
    target_year int not null,
    target_month int not null,
    amount int not null,
    created_at timestamp not null,
    updated_at timestamp not null,

    constraint uk_monthly_budgets_year_month
        unique (target_year, target_month),

    constraint ck_monthly_budgets_month
        check (target_month between 1 and 12),

    constraint ck_monthly_budgets_amount
        check (amount >= 0)
);

-- カテゴリー予算
create table category_budgets (
    id uuid primary key,
    target_year int not null,
    target_month int not null,
    category_id uuid not null,
    amount int not null,
    created_at timestamp not null,
    updated_at timestamp not null,

    constraint fk_category_budgets_category
        foreign key (category_id)
        references categories(id),

    constraint uk_category_budgets_year_month_category
        unique (target_year, target_month, category_id),

    constraint ck_category_budgets_month
        check (target_month between 1 and 12),

    constraint ck_category_budgets_amount
        check (amount >= 0)
);